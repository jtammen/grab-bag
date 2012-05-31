% RECombination Cycle
%
% Syntax:  NewChrom = reccycle(Chrom, RecRate)
%
% Input parameters:
%    Chrom     - Matrix containing the chromosomes of the old
%                population. Each row corresponds to one individual.
%    RecRate   - Probability of crossover ocurring between pairs
%                of individuals.
%
% Output parameter:
%    NewChrom  - Matrix containing the chromosomes of the population
%                after mating, ready to be mutated and/or evaluated,
%                in the same format as Chrom.
%
% Basiert auf recmp.m der GEATbx.

% Authors:  Andre Erb, Jan Tammen
% History:  22.06.07    file created


function Children = recgp(Parents, RecRate);

    % Groesse der Population und Chromosomlaenge bestimmen
    [sizePop, sizeChrom] = size(Parents);
    
    % Keine Rekombination moeglich
    if sizeChrom < 2, 
        Children = Parents;
        return;
    end

    % Wenn Rate nicht uebergeben wurde, Rate leer setzen
    if nargin < 2 | isnan(RecRate), 
        RecRate = []; 
    end
        
    % Wenn Rate leer ist, auf Default-Wert setzen
    if isempty(RecRate), 
        RecRate = 0.7; 
    end

    % Anzahl der Paare und Indizes der zu rekombinierenden Chromosome ermitteln
    numPairs = floor(sizePop/2);
    indOfChromToCross = find(rand(1, numPairs) < RecRate);
    
    ungeradeIndizes = 1:2:sizePop - 1;
    geradeIndizes = 2:2:sizePop;
      
    for chromIndex = indOfChromToCross
        % Zwei Elternchromosomen bestimmen
        parent1 = Parents(ungeradeIndizes(chromIndex), :);
        parent2 = Parents(geradeIndizes(chromIndex), :);
        
        % Alle nicht-gleichen Gene in den beiden Chromosomen finden,
        % um Endlosschleifen zu vermeiden
        indOfUnequalGenes = find(parent1 ~= parent2);
        
        % Leere Kindchromosomen mit Laenge der Elternchromosomen anlegen
        child1Chromosom = parent1;
        child2Chromosom = parent2;
     
        % Rekombinieren nur dann, wenn Chromosomen nicht komplett gleich sind
        if size(indOfUnequalGenes, 2) > 0
            % Eine zufaellige Startposition bestimmen
            startPosition = ceil(size(indOfUnequalGenes, 2).*rand(1,1));
            
            % Startwert ist der Wert an der Startposition des ersten Elternchromosoms
            startValue = parent1(indOfUnequalGenes(startPosition));
            cycle = indOfUnequalGenes(startPosition);
            
            % Zyklus erstellen
            while startValue ~= parent2(cycle(end)),
                % Position bestimmen, an der in Parent1 der Wert der selbe ist, wie der vorherige des Parent2
                index = indOfUnequalGenes(find(parent1(indOfUnequalGenes) == parent2(cycle(end))));
               
                % Zyklus speichern
                cycle = [cycle index];
            end % while
            
            % Gene an Positionen des Zyklus' zwischen den Kindern austauschen
            temp = child2Chromosom;
            child2Chromosom(cycle) = child1Chromosom(cycle);
            child1Chromosom(cycle) = temp(cycle);
            
        end % if
        
        % Kindchromosomen in Ergebnismatrix speichern
        Children(ungeradeIndizes(chromIndex), :) = child1Chromosom;
        Children(geradeIndizes(chromIndex), :) = child2Chromosom;
    end % for
      
    % Falls Populationsgroesse ungerade, letztes Chromosom der Eltern einfach uebernehmen
    if rem(sizePop, 2), 
        Children(sizePop, :) = Parents(sizePop, :);
    end
    
% Ende