function [gconfusion, kconfusion] = confusion()
    load true_900.mat;
    truedata = true_900;
    [gcluster,kcluster] = trueclusters();
    gconfusion = zeros(3,3);
    kconfusion = zeros(3,3);
    for i = 1:900
        a = truedata(i) ;
        b = gcluster(i) ;
        c = kcluster(i) ;
        gconfusion(b,a) = gconfusion(b,a) + 1;
        kconfusion(c,a) = kconfusion(c,a) + 1;
    end
    
end