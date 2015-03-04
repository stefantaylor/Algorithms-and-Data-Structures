function [errors] = sumsquarederrors()
    errors = [];
    for i = 2:5
        [clusteredpoints, clustermeans] = kmeanscluster(i, kMeans(i, findmeans(distanceMatrix)));
        a = 0;
        for i = 1:length(clusteredpoints)
           a = a + sum((clusteredpoints(i,1:3) - clustermeans(clusteredpoints(i,4),:)).^2); 
        end
        error = a/length(clusteredpoints);
        errors = [errors, error];
    end
end