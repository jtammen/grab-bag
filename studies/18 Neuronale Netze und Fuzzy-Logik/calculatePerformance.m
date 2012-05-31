% Ueberpruefen der Simulationsergebnisse - in jeder Spalte von ySim
% steht ein Ergebnis. In den ersten X Spalten muss Klasse 1 erkannt
% worden sein, dann Klasse 2 usw.
function [errors, success, totalSuccess] = calculatePerformance(ySim, tSim, simData)
errors       = [0 0 0];
success      = [0 0 0];
totalSuccess = 0;

numCols = length(ySim);
for col = 1:numCols
    class = -1;
    classValue = -1;
    for row = 1:3
        if ySim(row, col) > classValue,
            class = row;
            classValue = ySim(row, col);
        end
    end
    
    % Konnte keiner Klasse zugeordnet werden -> Fehler!
    if classValue < 0 | tSim(class, col) ~= 1
        if col <= length(simData{1}), 
            errors(1) = errors(1) + 1; 
        elseif col <= length(simData{1}) + length(simData{2}),
            errors(2) = errors(2) + 1;
        else
            errors(3) = errors(3) + 1;
        end
    end
end

success(1) = ( 1 - errors(1)/length(simData{1}) ) * 100;
success(2) = ( 1 - errors(2)/length(simData{2}) ) * 100;
success(3) = ( 1 - errors(3)/length(simData{3}) ) * 100;
totalSuccess = ( 1 - sum(errors)/numCols ) * 100;