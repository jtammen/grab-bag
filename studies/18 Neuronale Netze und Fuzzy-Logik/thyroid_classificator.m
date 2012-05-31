%%% Neuronale Netze, SS 07
%%% Jan Tammen, 277143

clear;

% Einlesen der Daten
thyroid = load('thyroid.dat');

% Aufteilen der Daten in die drei Klassen
% In der letzten Spalte (22) steht die Klassennummer
class1Samples = thyroid(find(thyroid(:,22) == 1), :);
class2Samples = thyroid(find(thyroid(:,22) == 2), :);
class3Samples = thyroid(find(thyroid(:,22) == 3), :);

% Nun X Prozent jeder Klasse als Trainingdatensaetze auswaehlen
percentTrainData = 0.50;
numClass1 = ceil(percentTrainData*size(class1Samples,1)); 
numClass2 = ceil(percentTrainData*size(class2Samples,1));
numClass3 = ceil(percentTrainData*size(class3Samples,1));
class1TrainData = class1Samples(1:numClass1, 1:21);
class2TrainData = class2Samples(1:numClass2, 1:21);
class3TrainData = class3Samples(1:numClass3, 1:21);

% Komplette Trainingsdaten (p) zusammenfuegen. Matrix muss dazu
% transponiert werden, da jeder Datensatz in einer _Spalte_ stehen muss
completeTrainData = [class1TrainData; class2TrainData; class3TrainData];
p = completeTrainData';

% Matrix mit den Zielwerten (target) erstellen, 3 Ausgabeneuronen
% Klasse 1: 1 0 0
% Klasse 2: 0 1 0
% Klasse 3: 0 0 1
tClass1 = repmat([1; 0; 0], 1, numClass1);
tClass2 = repmat([0; 1; 0], 1, numClass2);
tClass3 = repmat([0; 0; 1], 1, numClass3);
t = horzcat(tClass1, tClass2, tClass3);

% Das neuronale Netz mit den ermittelten Parametern erstellen
net = newff(minmax(p), [45, 45, 3], {'logsig', 'logsig', 'logsig'}, 'trainrp');
net = init(net);

net.performFcn = 'mse';       % Performance-Funktion
net.trainParam.show = 10;     % Ausgabe alle x Epochen
net.trainParam.epochs = 2000; % Epochen
net.trainParam.goal = 0.0001; % Fehlerziel

[net, tr, yTrain, eTrain] = train(net, p, t);

% Netz simulieren
disp('Training abgeschlossen');
pause; clc;

% Simulation mit den verbleibenden, nicht zum Training verwendeten Daten
class1SimData = class1Samples(numClass1+1:size(class1Samples, 1), 1:21);
class2SimData = class2Samples(numClass2+1:size(class2Samples, 1), 1:21);
class3SimData = class3Samples(numClass3+1:size(class3Samples, 1), 1:21);
simulationData = [class1SimData; class2SimData; class3SimData];
tSimClass1 = repmat([1; 0; 0], 1, length(class1SimData));
tSimClass2 = repmat([0; 1; 0], 1, length(class2SimData));
tSimClass3 = repmat([0; 0; 1], 1, length(class3SimData));
% In tSim stehen die SOLL-Werte
tSim = horzcat(tSimClass1, tSimClass2, tSimClass3);
[ySim, pf, af, eSim, perf] = sim(net, simulationData');
[errors, success, totalSuccess] = calculatePerformance(ySim, tSim, ...
    {class1SimData; class2SimData; class3SimData});

disp('Simulation mit Komplementaermenge abgeschlossen');
disp(sprintf('Fehler: C1: %d/%d (OK: %f), C2: %d/%d (OK: %f), C3: %d/%d (OK: %f)',...
    errors(1), length(class1SimData), success(1),...
    errors(2), length(class2SimData), success(2),...
    errors(3), length(class3SimData), success(3)));
disp(sprintf('Klassifikationsguete gesamt: %f', totalSuccess));