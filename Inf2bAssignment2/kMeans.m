function [newmeans] = kMeans(x, means)
    load data_90.mat;
    rows = length(means(:,1));
    v = [];
    a = 1;
    if x>2
        for i = 1:90 
            for j = 1:rows
                a = a * (sqrt(((data_90(i,1) - means(j,1))^2)+((data_90(i,2) - means(j,2))^2)+((data_90(i,3) - means(j,3))^2)));
            end
            v = [v,a];
            a=1;
        end
        pos = find(v==max(v));
        newmeans = [means; data_90(pos(1),:)];
        newmeans = kMeans((x-1),newmeans);
    else newmeans = means;    
    end
end