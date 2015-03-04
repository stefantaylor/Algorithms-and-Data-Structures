function [covarmats] = covar(k)
      [clusteredpoints, clustermeans] = kmeanscluster(k, kMeans(k, findmeans(distanceMatrix)));
      covarmats = zeros((3*k),3);
      for j = 1:k
          c = zeros(3);
          n = 0;
          for i=1:length(clusteredpoints)
              if clusteredpoints(i,4) == j
                  a = clusteredpoints(i,1:3) - clustermeans(j,:);
                  b = transpose(a);
                  c = c + (b * a);
                  n = n+1;
              end
          end
          c = c / n;
          alpha = (3*j) - 2;
          beta = (3*j);
          covarmats(alpha:beta, 1:3) = c;
      end
end