function [gcluster, kcluster] = trueclusters()

    gcluster = classify900;
    for i = 1:length(gcluster)
        if gcluster(i,1) == 2
             gcluster (i,1) = 3;
        elseif gcluster(i,1) == 3
             gcluster (i,1) = 2;
        end
    end
    
    kcluster = kmeanscluster900(3, kMeans(3, findmeans(distanceMatrix)));
    for i = 1:length(kcluster)
        if kcluster(i,1) == 2
             kcluster (i,1) = 3;
        elseif kcluster(i,1) == 3
             kcluster (i,1) = 2;
        end
    end
    
end