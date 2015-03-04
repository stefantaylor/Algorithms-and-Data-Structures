function [cluster] = classify900()
    load data_900.mat;
    data = data_900;
    covars = covar(3);
    covar1 = covars(1:3,1:3);
    covar2 = covars(4:6,1:3);
    covar3 = covars(7:9,1:3);
    means = kMeans(3, findmeans(distanceMatrix));
    mean1 = means(1,:);
    mean2 = means(2,:);
    mean3 = means(3,:);
    cluster = zeros(900,1);
    %pdf = zeros(900,1);
    for i = 1:length(data_900)
        x = data(i,:);
        a = ((1/(((2*pi)^1.5)*(det(covar1)^0.5)))*exp((-0.5*(x - mean1)/covar1)*transpose(x - mean1)));
        b = ((1/(((2*pi)^1.5)*(det(covar2)^0.5)))*exp((-0.5*(x - mean2)/covar2)*transpose(x - mean2)));
        c = ((1/(((2*pi)^1.5)*(det(covar3)^0.5)))*exp((-0.5*(x - mean3)/covar3)*transpose(x - mean3)));
         if max(a,max(b,c)) == a
             cluster(i,1) = 1;
         elseif max(a,max(b,c)) == b
             cluster(i,1) = 2;
         else
             cluster(i,1) = 3;
         end
        %pdf(i,1) = max(a,max(b,c));
    end
end