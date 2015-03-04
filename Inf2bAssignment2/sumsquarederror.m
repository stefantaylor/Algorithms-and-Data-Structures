function [error] = sumsquarederror()
    [clusteredpoints, clustermeans] = kmeanscluster(4, kMeans(4, findmeans(distanceMatrix)));
    a = 0;
    for i = 1:length(clusteredpoints)
       a = a + sum((clusteredpoints(i,1:3) - clustermeans(clusteredpoints(i,4),:)).^2); 
    end
    error = a/length(clusteredpoints);
end