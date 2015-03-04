function [clustermatrix, newmeans] = kmeanscluster (k, means)
    load data_90.mat;
    oldmeans = zeros(k,3);
    newmeans = means;
    while ~(isequal(newmeans,oldmeans))
        m=[];
        clustertable = zeros(k,4);
        clustermatrix = [];
        
        
        
        
        %portion concerned with clustering points basedo n given means
        for i = 1:length(data_90)
            v = [];
            for j = 1:length(newmeans(:,1))
                v = [v, (sqrt(((data_90(i,1) - newmeans(j,1))^2)+((data_90(i,2) - newmeans(j,2))^2)+((data_90(i,3) - newmeans(j,3))^2)))];
            end
            m = [m;v];
        end
        for i = 1:length(m)
           cluster = find(m(i,:)==min(m(i,:)));
           clustermatrix = [clustermatrix ; [data_90(i,:),cluster]];
        end
        
        
        
        
        %portion concerned with recalculating means based on new clusters
        width = length(clustermatrix(1,:));
        for i = 1:length(clustermatrix)
            for j = 1:k
                if clustermatrix(i,width)==j
                    clustertable(j,1:3) = clustertable(j,1:3) + clustermatrix(i,1:3);
                    clustertable(j,4) = clustertable(j,4) + 1;
                end
            end
        end
        oldmeans = newmeans;
        for i = 1:k
            newmeans(i,:) = clustertable(i,1:3)/clustertable(i,4);
        end
        
        
        
        
    end    
    
end