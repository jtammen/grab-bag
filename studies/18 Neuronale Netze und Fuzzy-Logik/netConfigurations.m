oneHiddenLayerRuns = [5, 10, 15, 20, 25, 30, 35, 40, 45, 50];
twoHiddenLayerRuns = [10, 15, 30, 35, 45];

for numHiddenNeurons = 1 : size(twoHiddenLayerRuns, 2)
    for run = 1 : 10
        % Neuronales Netz definieren
        % Alternative Aktivierungsfkt.: tansig, purelin
        % Alternative Trainingsfkt.:    trainlm, traingdx, trainrp
        net = newff(minmax(p), [twoHiddenLayerRuns(numHiddenNeurons), twoHiddenLayerRuns(numHiddenNeurons), 3], ...
                {'logsig', 'logsig', 'logsig'}, 'trainrp');
        net = init(net);
        
        % Trainingsparameter
        %net.trainParam.mem_reduc = 2; % Fuer trainlm
        %net.trainParam.lr = 0.01;
        %net.trainParam.mc = 0.9;
        
        net.performFcn = 'mse';       % Performance-Funktion
        net.trainParam.show = 10;    % Ausgabe alle x Epochen
        net.trainParam.epochs = 2000; % Epochen
        net.trainParam.goal = 0.0001; % Fehlerziel
        
        [net, tr, y, e] = train(net, p, t);
        goalMet = 0;
        if (mse(e) <= net.trainParam.goal), goalMet = 1; end
        logToLogFile('thyroid.log', ...
            sprintf('Neurons: %d @ Run: %d ### BTF: %s\nTF: logsig\nPF: %s, Goal: %d\nEpoche: %d\nMSE: %d\nGoal met: %d\n', ...
            twoHiddenLayerRuns(numHiddenNeurons), run, net.trainFcn, net.performFcn, net.trainParam.goal, ...
            size(tr.epoch, 2), mse(e), goalMet));
        % Plot speichern
        global savefile;
        savefile = sprintf('21-%d-%d-3_%d.eps', twoHiddenLayerRuns(numHiddenNeurons), ...
            twoHiddenLayerRuns(numHiddenNeurons), run);
        plotperf(tr);
    end
    logToLogFile('thyroid.log', sprintf('================================\n'));
end