function [] = Master()
%UNTITLED2 Summary of this function goes here
%   Detailed explanation goes here
    
    %[a,b] = kmeanscluster(3, kMeans(3, findmeans(distanceMatrix)))

    plot([sumsquarederrors]);
    
    question2 = kmeanscluster(3, kMeans(3, findmeans(distanceMatrix)))
    
    %Question3
    [covar1, mean1, covar2, mean2, covar3, mean3] = Gaussians()
        
    question4 = classify900
    
    question5 = kmeanscluster900(3, kMeans(3, findmeans(distanceMatrix)))
    
    %Question6
    [a,b] = confusion()
    
    

end

