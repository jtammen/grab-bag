% Neuronale Netze im SS2007
% Aufgabe 1: Schilddrüsenfunktion
% Christian Kungel, SE8, Matrikelnummer 279475

clear all;
echo off;

% lade Schilddrüsenfunktionsdaten
Daten = load('thyroid.txt');

% Lernaufgabe:
von = 1;
bis = 7200;

% Drei Klassen (1 = ,2 = ,3 = )
c1 = 1;
c2 = 2;
c3 = 3;

% finde Indizes für Klasse 1, 2, 3
c1Ind = find(Daten(:,22) == c1);
c2Ind = find(Daten(:,22) == c2);
c3Ind = find(Daten(:,22) == c3);

% Daten splitten in die drei Klassen aufgrund der Indizes
c1Daten = Daten(c1Ind, :);
c2Daten = Daten(c2Ind, :);
c3Daten = Daten(c3Ind, :);

% hole aus jeder der drei Klassen 10% an Trainingsdaten, Anzahl nach unten runden
Anzahlc1Daten = floor(0.1*size(c1Daten, 1));
Anzahlc2Daten = floor(0.1*size(c2Daten, 1));
Anzahlc3Daten = floor(0.1*size(c3Daten, 1));

% Trainingsdaten für die Klassen
c1TrainingsDaten = c1Daten(1:Anzahlc1Daten, :);
c2TrainingsDaten = c2Daten(1:Anzahlc2Daten, :);
c3TrainingsDaten = c3Daten(1:Anzahlc3Daten, :);

% Matriz mit kompletten Trainingsdaten (p) erstellen (vertikal konkatenieren)
Trainingsdaten = vertcat(c1TrainingsDaten, c2TrainingsDaten, c3TrainingsDaten);
p = Trainingsdaten'; 

% Drei Ausgabeneuronen für die Klassen (3 Bit)
% Klasse 1: 0 0 1
% Klasse 2: 0 1 0
% Klasse 3: 1 0 0

% Klasse 1
t(1, 1:Anzahlc1Daten) = 0;
t(1, Anzahlc1Daten+1:Anzahlc1Daten+Anzahlc2Daten) = 0;
t(1, Anzahlc2Daten+1:Anzahlc1Daten+Anzahlc2Daten+Anzahlc3Daten) = 1;

% Klasse 2
t(2, 1:Anzahlc1Daten) = 0;
t(2, Anzahlc1Daten+1:Anzahlc1Daten+Anzahlc2Daten) = 1;
t(2, Anzahlc2Daten+1:Anzahlc1Daten+Anzahlc2Daten+Anzahlc3Daten) = 0;

% Klasse 3
t(3, 1:Anzahlc1Daten) = 1;
t(3, Anzahlc1Daten+1:Anzahlc1Daten+Anzahlc2Daten) = 0;
t(3, Anzahlc2Daten+1:Anzahlc1Daten+Anzahlc2Daten+Anzahlc3Daten) = 0;


% Definition des Neuronalen Netzes
AnzahlVerdeckteSchichten = 20;
AnzahlAusgabeNeuronen = 3;

f = 'logsig';								                  % Aktivierungsfunktion
net = newff(minmax(p), [AnzahlVerdeckteSchichten, AnzahlAusgabeNeuronen],{f,f});  % 21-X-3-Netz
% net = init(net);							                  % Initialisierung
% net.IW{1,1}		                                              % Gewichts-Matrix zwischen Eingabeschicht und Schicht 1;
% net.b{1}			                                          % Bias-Vektor für Schicht 1
% net.LW{2,1}		                                              % Gewichts-Matrix zwischen Schicht 1 und Schicht 2
% net.b{2}			                                          % Bias-Vektor für Schicht 2

% Alternative: Zufällige Initialisierung der Gewichte und Bias:
net.layers{1}.initFcn = 'initwb';
net.layers{2}.initFcn = 'initwb';
net.inputWeights{1,1}.initFcn = 'rands';
net.layerWeights{2,1}.initFcn = 'rands';
net.biases{1}.initFcn = 'rands';
net.biases{1}.initFcn = 'rands';
net = init(net);
net
pause


% Training des aufgebauten Netzes: Alternativen traingdx (Gradientenabstieg), trainrp
% (Resilient Backpropagation), trainlm (Levenberg-Marquardt)
net.trainFcn = 'trainlm';		% Gradientenabstieg mit konst. Lernrate und Momentum
net.trainParam.lr = 0.9;        % Lernrate
net.trainParam.mc = 0.9;        % Momentum

% Alternativen
%net.trainFcn = 'traingdx'; 	% Gradientenabstieg mit adativer Lernrate
%net.trainParam.mc = 0.9;	    % und Momentum
%net.trainFcn = 'trainrp';		% Resilient Backpropagation
%net.trainFcn = 'trainlm';		% Levenberg-Marquardt

AnzahlEpochen = 2000;

net.performFcn = 'mse';		            % Performance-Fkt des Netzes ist sum squared error (sse)
net.trainParam.show = 20;               % Display-Rate
net.trainParam.epochs = AnzahlEpochen;  % Max Anz. Epochen
net.trainParam.goal = 0.0003;	            % Fehlerziel: SSE = 0

% Lernphase
[net, tr] = train(net,p,t);
tr

disp('Netz wurde trainiert (Taste drücken für Anwendung des Netzes)');
pause;
clc;

SimulationsDaten = Daten'; 
% Netz anwenden
[Y,Pf,Af,E,perf] = sim(net,p);
Y
perf


% t(3, 1:Anzahlc1Daten) = 1;
% t(3, Anzahlc1Daten+1:Anzahlc1Daten+Anzahlc2Daten) = 0;
% t(3, Anzahlc2Daten+1:Anzahlc1Daten+Anzahlc2Daten+Anzahlc3Daten) = 0;
% 
% x=0; %3
% y=0; %2
% z=0; %1
% 
% a=0;

%round(x*1e4)*1e-4

% for i = 1:7200
%     if (short(o(1,i)) == 1.0000 && round((o(1,i)*1e4)*1e-4) == 0.0000 && round((o(1,i)*1e4)*1e-4) == 0.0000)
%         x=x+1;
%     elseif (round((o(1,i)*1e4)*1e-4) == 0 && round((o(1,i)*1e4)*1e-4) == 1 && round((o(1,i)*1e4)*1e-4) == 0)
%         y=y+1;
%     elseif (round((o(1,i)*1e4)*1e-4) == 1 && round((o(1,i)*1e4)*1e-4) == 0 && round((o(1,i)*1e4)*1e-4) == 0)
%         z=z+1;
%     else
%         a=a+1;
%   end
% end
% 
% % 
% x
% y
% z
% a