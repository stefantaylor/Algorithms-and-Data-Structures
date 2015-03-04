function [dmatrix] = distanceMatrix
load data_90.mat;
dmatrix = zeros(90);
for x = 1:length(data_90)
    for y = 1:length(data_90)
        dmatrix(x,y) = sqrt(((data_90(y,1) - data_90(x,1))^2)+((data_90(y,2) - data_90(x,2))^2)+((data_90(y,3) - data_90(x,3))^2));
        
    end
end
end
