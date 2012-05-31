%%% Autoren:  Andre Erb, Jan Tammen
%%% Erstellt: 2007-05-25

% Fuer Aufgabe c), je 20 Testlaeufe, insgesamt: 4 * 4 * 20 = 320
subpopulations = [1, 5, 8, 12];
individuals = [15, 30, 45, 60];
numTestRuns = 20;
numSubpopRuns = 4;
numIndivRuns = 4;

% Default-Parameter holen
GeaOpt = geaoptset(tbx3perm);

% Subpopulationen
for indiv = 1 : numSubpopRuns
   
   % Individuen pro Subpopulation
   for subpop = 1 : numIndivRuns
      
      % Werte initialisieren
      min = 1000000;	% Minimalwert
      minNumGen = 0;	% Generation, in der Minimalwert auftrat
      minNumRun = 0; % Testlauf, in dem Minimalwert auftrat
      max = 0;			% Maximalwert
      maxNumGen = 0;	% Generation, in der Maximalwert auftrat
      maxNumRun = 0; % Testlauf, in dem Maximalwert auftrat
      
      % Alle Ergebnisse in einem Array ablegen, damit spaeter Mittelwert/Std.abw.
      % berechnet werden koennen
      results = zeros(numTestRuns, 1);
      index = 1;		% Index des Ergebnis-Arrays
      
      % Schleife fuer die Testlaeufe
      for run = 1 : numTestRuns
         disp(sprintf('Individuen: %d, Subpop: %d, Testlauf: %d', individuals(indiv), subpopulations(subpop), run));
                  
         % Setzen der Parameter
         GeaOpt = geaoptset( GeaOpt ...
         	% Anzahl der Subpopulationen
            , 'NumberSubpopulation',        subpopulations(subpop) ... 
            % Anzahl der Individuen pro Subpopulation
            , 'NumberIndividuals',          individuals(indiv) ...
            , 'Output.TextInterval',        0 ...        
            ...  
            , 'Output.GrafikInterval',      0 ... 
            , 'Output.GrafikMethod',        [111111] ...
            , 'Output.GrafikStyle',         [514143] ...
            ...
            , 'Output.StatePlotInterval',  0          ...
            , 'Output.StatePlotFunction', 'plottsplib' ...
            ...
            % Ende nach 100 Generationen
            , 'Termination.Method',         [1] ...
            , 'Termination.MaxGen',         100 ...
            ...
            % Selektionsverfahren: selsus, selrws, seltour
            , 'Selection.Name',             'selsus' ... 
            % Rekombination: recgp, recpm, reccycle 
            , 'Recombination.Name',         'recgp'  ... 
             % Mutationsrate
            , 'Mutation.Rate',              0.9      ...
            % Mutationsverfahren
            , 'Mutation.Name', 				  {'mutswap', 'mutmove', 'mutinvert'} ... 
            );
         
         % Speichern der Ergebnisse
         GeaOpt = geaoptset( GeaOpt ...
            , 'Output.SaveTextInterval',    20 ...       
            , 'Output.SaveTextFilename',    'tsp.log' ...
            );
         
         % Auswaehlen der Zielfunktion
         GeaOpt = geaoptset( GeaOpt , 'System.ObjFunFilename', 'objtsplib'); objfun = [];
         
         % TSP-Daten laden
         global TSPLIB_FILENAME;
         global TSPLIB_NAME;
         TSPLIB_FILENAME = 'bays29'; TSPLIB_NAME = '';
         tsp_readlib(TSPLIB_FILENAME);
         GeaOpt = geaoptset( GeaOpt , 'System.ObjFunAddPara', {TSPLIB_NAME});
         
         % Ober- und Untergrenzen der Zielfunktion holen
         VLUB = geaobjpara(GeaOpt.System.ObjFunFilename, 1, GeaOpt.System.ObjFunAddPara)
         GeaOpt = geaoptset( GeaOpt , 'System.ObjFunVarBounds', VLUB); VLUB = [];
         
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
      disp(sprintf('Mittelwert von %g Runs: %.2f', numTestRuns, mean(results)));
      disp(sprintf('Standardabweichung von %g Runs: %.2f', numTestRuns, std(results)));
      disp(sprintf('Min: %g (Run: %g, Generation: %g), Max: %g (Run: %g, Generation: %g)', ... 
         min, minNumRun, minNumGen, max, maxNumRun, maxNumGen));
      logToLogfile(GeaOpt.Output.SaveTextFilename, ...
         sprintf('Ergebnisse: %s\nMin: %g (Run: %g, Generation: %g), Max: %g (Run: %g, Generation: %g)\nMittelwert von %g Runs: %.2f\nStandardabweichung: %.2f', ...
         sprintf('%g ', results), min, minNumRun, minNumGen, max, maxNumRun, maxNumGen, ...
         numTestRuns, mean(results), std(results)));
   end
   
end