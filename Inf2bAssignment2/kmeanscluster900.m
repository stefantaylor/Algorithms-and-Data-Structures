function [clusters] = kmeanscluster900 (k, means)
    load data_900.mat;
    oldmeans = zeros(k,3);
    newmeans = means;
    while ~(isequal(newmeans,oldmeans))
        m=[];
        clustertable = zeros(k,4);
        clustermatrix = [];

        for i = 1:length(data_900)
            v = [];
            for j = 1:length(newmeans(:,1))
                v = [v, (sqrt(((data_900(i,1) - newmeans(j,1))^2)+((data_900(i,2) - newmeans(j,2))^2)+((data_900(i,3) - newmeans(j,3))^2)))];
            end
            m = [m;v];
        end
        for i = 1:length(m)
           cluster = find(m(i,:)==min(m(i,:)));
           clustermatrix = [clustermatrix ; [data_900(i,:),cluster]];
        end

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
    clusters = clustermatrix(:,4);
    
end