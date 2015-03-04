function [means] = findmeans(dmatrix)
    load data_90.mat;
    [r,c] = find(dmatrix == max(max(dmatrix)));
    mean1 = data_90(r(1),:);
    mean2 = data_90(c(1),:);
    means = [mean1; mean2];
    
    %    [r,c] = find(distanceMatrix == max(max(distanceMatrix)));

end

