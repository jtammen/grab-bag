function ObjVal = objfun_schwefel(Chromosom, option);
    [Nind, Nvar] = size(Chromosom);

    if Nind > 0
        % sum of -xi * sin(sqrt(abs(xi))) for i = 1:Nvar (Nvar = 10)
        % n = Nvar, -500 <= xi <= 500
        % global minimum at (xi) = (420.9687) ; fmin = -n * 420.9687
        ObjVal = sum((-Chromosom.*sin(sqrt(abs(Chromosom)))), 2);
    else
        Dim = 2;
        if option == 2
            ObjVal = ['Schwefel-Funktion'];
        elseif option == 3
            xmin = 420.9687;
            ObjVal = Dim * (-xmin * sin(sqrt(abs(xmin))));
        else
            %'Hallo'
            %ObjVal = repmat([-500,-500;500,500], [2 Dim]);        
        end
    end   
end