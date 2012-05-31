% Einlesen der Daten
thyroid = load('thyroid.txt');

% Aufteilen der Daten in die drei Klassen
% In der letzten Spalte (22) steht die Klassennummer
class1Samples = thyroid(find(thyroid(:,22) == 1), :);
class2Samples = thyroid(find(thyroid(:,22) == 2), :);
class3Samples = thyroid(find(thyroid(:,22) == 3), :);

% Nun p Prozent jeder Klasse als Trainingdatensaetze auswaehlen
p = 0.1;
numClass1 = floor(p*size(class1Samples,1)); 
numClass2 = floor(p*size(class2Samples,1));
numClass3 = floor(p*size(class3Samples,1));
class1TrainData = thyroid(1:numClass1, :);
class2TrainData = thyroid(1:numClass2, :);
class3TrainData = thyroid(1:numClass3, :);

% Komplette Trainingsdaten (p) zusammenfuegen
completeTrainData = [class1TrainData; class2TrainData; class3TrainData];
p = completeTrainData';

% Matrix mit den Zielwerten (target) erstellen, 3 Ausgabeneuronen
% Klasse 1: 1 0 0
% Klasse 2: 0 1 0
% Klasse 3: 0 0 1
tClass1 = repmat([1 0 0], 1, numClass1);
tClass2 = repmat([0 1 0], 1, numClass2);
tClass3 = repmat([0 0 1], 1, numClass3);


% Neuronales Netz definieren