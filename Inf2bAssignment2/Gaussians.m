function [covar1, mean1, covar2, mean2, covar3, mean3] = Gaussians()
    covars = covar(3);
    covar1 = covars(1:3,1:3);
    covar2 = covars(4:6,1:3);
    covar3 = covars(7:9,1:3);
    means = kMeans(3, findmeans(distanceMatrix));
    mean1 = means(1,:);
    mean2 = means(2,:);
    mean3 = means(3,:);
    
    
end