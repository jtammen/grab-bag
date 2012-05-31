% Aufgabe 2, Genetische Algorithmen SS 07
GeaOpt = tbx3bin;
GeaOpt = geaoptset( GeaOpt , tbx3bin);

% Parameter einstellen
GeaOpt = geaoptset( GeaOpt ...
    , 'VariableFormat',             1 ...        % Format 1 oder 4!?
    , 'NumberSubpopulation',        1 ...        % Number of subpopulation
    , 'NumberIndividuals',         [50] ...      % Number of individuals per subpopulation
    , 'Output.TextInterval',        5 ...        % Text output every 5 generations
    ...   
    , 'Output.GrafikInterval',      20 ...       % Grafic results every 10 generations
    , 'Output.GrafikMethod',        111111 ...   % Grafic method to use
    , 'Output.GrafikStyle',         614121 ...   % Grafic styles for specified methods
    ...
    , 'Termination.Method',         [1  ] ...    % Termination method(s) to use
    , 'Termination.MaxGen',         200 ...      % Terminate after xx generations
    , 'Termination.MaxTime',        1 ...        % Terminate after xx minutes
    ...    
    , 'Selection.Name',             'selrws' ... % Selektion: Roulette-Wheel
    , 'Recombination.Name',         'recsp'  ... % Rekombination: One-Point-Crossover
    , 'Recombination.Rate',         0.7      ... % Rekombination: Rate
    , 'Mutation.Name',              'mutbin' ... % Mutation: Bit-Flip
    , 'Mutation.Rate',              0.9      ... % Mutation: Rate
    );

VLUB = [-500, -500; 500, 500];
[xnew, GeaOpt] = geamain2('objfun_schwefel', GeaOpt, VLUB, []);