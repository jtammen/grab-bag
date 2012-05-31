%%% Autoren:  Andre Erb, Jan Tammen
%%% Erstellt: 2007-05-25

% Für Aufgabe f), je 20 Testläufe
subpopulations = 12;
individuals 	= 60;
numTestRuns 	= 20;

% Für Aufgabe f)
allMutations  = {'mutswap', 'mutmove', 'mutinvert'};
mutationRates = [0.001, 0.005, 0.01, 0.05, 0.08, 0.1, 0.2, 0.3, 0.4, 0.5, 1, 3, 5, 10, 15, 20, 25, 30];

% Default-Parameter holen
GeaOpt = geaoptset(tbx3perm);

for actMutationRate = 1 : 18
   
   % Werte initialisieren
   min 			= 1000000;	% Minimalwert
   minNumGen 	= 0;			% Generation, in der Minimalwert auftrat
   minNumRun   = 0;			% Testlauf, in dem der Minimalwert auftrat
   max 			= 0;			% Maximalwert
   maxNumGen 	= 0;			% Generation, in der Maximalwert auftrat
   maxNumRun   = 0;			% Testlauf, in dem der Maximalmalwert auftrat
   
   
   % Alle Ergebnisse in einem Array ablegen, damit später Mittelwert/Std.abw. berechnet werden können
   results = zeros(numTestRuns, 1);
   index = 1;					% Index des Ergebnis-Arrays
   
   
   % Schleife für die Testläufe
   for run = 1 : numTestRuns
      disp(sprintf('Subpop: %d, Individuen: %d, Testlauf: %d', subpopulations, individuals, run));
      
      % Setzen der Parameter
      GeaOpt = geaoptset( GeaOpt ...
         , 'NumberSubpopulation',        	subpopulations ... 		% Number of subpopulation
      	, 'NumberIndividuals',          	individuals ...      	% Number of individuals per subpopulation
      	, 'Output.TextInterval',        	0 ...                	% Text output every 5 generations
         ...  
         , 'Output.GrafikInterval',      	0 ...              		% Grafic results every 10 generations
         , 'Output.GrafikMethod',        	[111111] ...       		% Grafic method to use
         , 'Output.GrafikStyle',         	[514143] ...       		% Grafic styles for specified methods
         ...
         , 'Output.StatePlotInterval',  	0 ...
         , 'Output.StatePlotFunction', 	'plottsplib' ...
         ...
         , 'Termination.Method',         	[1] ...    								% Termination method to use
         , 'Termination.MaxGen',         	100 ...      							% Terminate after xx generations
         ...
         , 'Selection.Name',             	'selsus' ...							% Selektion: selsus
         , 'Recombination.Name',         	'recgp'  ... 							% Rekombination: recgp, recpm, reccycle (selbst implementieren!) 
         , 'Mutation.Rate',              	mutationRates(actMutationRate)...% Mutation-Rate
         , 'Mutation.Name', 				  	allMutations  ... 					% ALLE Mutation: Verfahren
         );
      
      % Speichern der Ergebnisse
      GeaOpt = geaoptset( GeaOpt ...
         , 'Output.SaveTextInterval',    20 ...        				% Text to File every xx generations
         , 'Output.SaveTextFilename',    'loggingAufgabeF.log' ...% Filename of result file, absolut or relative path may be included
         );
      
      % Auswählen der Zielfunktion
      GeaOpt = geaoptset( GeaOpt , 'System.ObjFunFilename', 'objtsplib'); 
      objfun = [];
      
      % TSP-Daten laden
      global TSPLIB_FILENAME;
      global TSPLIB_NAME;
      TSPLIB_FILENAME = 'bays29'; 
      TSPLIB_NAME = '';
      tsp_readlib(TSPLIB_FILENAME);
      GeaOpt = geaoptset( GeaOpt , 'System.ObjFunAddPara', {TSPLIB_NAME});
      
      % Ober- und Untergrenzen der Zielfunktion holen
      VLUB = geaobjpara(GeaOpt.System.ObjFunFilename, 1, GeaOpt.System.ObjFunAddPara)
      GeaOpt = geaoptset( GeaOpt , 'System.ObjFunVarBounds', VLUB); 
      VLUB = [];
      
      % Aufruf der Haupt-Funktion
      [xnew, GeaOpt] = geamain2(objfun, GeaOpt, VLUB, []);
      
      BestResult = GeaOpt.Run.BestObjectiveValue;
      PosBest = GeaOpt.Run.PosBest;
      
      % Min- und Max-Wert bestimmen
      if BestResult < min
         min = BestResult;
         minNumGen = PosBest(1);
         minNumRun = run;
      end
      if BestResult > max
         max = BestResult;
         maxNumGen = PosBest(1);
         maxNumRun = run;
      end
      
      % Speichern des Ergebnisses und Inkrementieren des Array-Indizes
      results(index) = BestResult;
      index = index+1;
      
   end
   
   % Ausgabe der Ergebnisse
   disp(results);
   disp(sprintf('Mittelwert von %g Testläufen: %.2f', numTestRuns, mean(results)));
   disp(sprintf('Standardabweichung von %g Testläufen: %.2f', numTestRuns, std(results)));
   disp(sprintf('Min: %g (Generation: %g in Testlauf: %g), Max: %g (Generation: %g in Testlauf: %g)', ...
      min, minNumGen, minNumRun, max, maxNumGen, maxNumRun));
   logToLogfile(GeaOpt.Output.SaveTextFilename, ...
      sprintf(' Ergebnisse: %s\n Min: %g (Generation: %g in Testlauf: %g), Max: %g (Generation: %g in Testlauf: %g)\n Mittelwert von %g Testläufen: %.2f\n Standardabweichung: %.2f', ...
      sprintf('%g ', results), min, minNumGen, minNumRun, max, maxNumGen, ...
      maxNumRun, numTestRuns, mean(results), std(results)));
   
end