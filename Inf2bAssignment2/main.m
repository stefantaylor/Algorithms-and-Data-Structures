function [] = main()
    load data_90.mat;
    load data_900.mat;
    [gcluster,kcluster] = trueclusters();
    gcluster1 = [];
    gcluster2 = [];
    gcluster3 = [];
    kcluster1 = [];
    kcluster2 = [];
    kcluster3 = [];
    kmc = kmeanscluster(3, kMeans(3, findmeans(distanceMatrix)));
    kmcc = kmc(:,4);
    kmcc1 = [];
    kmcc2 = [];
    kmcc3 = [];
    a = data_900;
    b = data_90;
    for i=1:900
        if gcluster(i) == 1
            gcluster1 = [gcluster1; a(i,:)];
        elseif gcluster(i) == 2
            gcluster2 = [gcluster2; a(i,:)];
        else
            gcluster3 = [gcluster3; a(i,:)];
        end
        if kcluster(i) == 1
            kcluster1 = [kcluster1; a(i,:)];
        elseif kcluster(i) == 2
            kcluster2 = [kcluster2; a(i,:)];
        else
            kcluster3 = [kcluster3; a(i,:)];
        end
    end
    for i = 1:90
        if kmcc(i) == 1
            kmcc1 = [kmcc1; b(i,:)];
        elseif kmcc(i) == 2
            kmcc2 = [kmcc2; b(i,:)];
        else
            kmcc3 = [kmcc3; b(i,:)];
        end
    end
    figure;
    plot(sumsquarederrors);
    figure;
    scatter3(kmcc1(:,1),kmcc1(:,2),kmcc1(:,3),'filled');
    hold on;
    scatter3(kmcc2(:,1),kmcc2(:,2),kmcc2(:,3),'filled');
    hold on;
    scatter3(kmcc3(:,1),kmcc3(:,2),kmcc3(:,3),'filled');
    hold off;
    figure;
    scatter3(gcluster1(:,1),gcluster1(:,2),gcluster1(:,3),'filled');
    hold on;
    scatter3(gcluster2(:,1),gcluster2(:,2),gcluster2(:,3),'filled');
    hold on;
    scatter3(gcluster3(:,1),gcluster3(:,2),gcluster3(:,3),'filled');
    hold off;
    figure;
    scatter3(kcluster1(:,1),kcluster1(:,2),kcluster1(:,3),'filled');
    hold on;
    scatter3(kcluster2(:,1),kcluster2(:,2),kcluster2(:,3),'filled');
    hold on;
    scatter3(kcluster3(:,1),kcluster3(:,2),kcluster3(:,3),'filled');
    hold off;
    
    [Covariance_Matrix_1,Mean_1,Covariance_Matrix_2,Mean_2,Covariance_Matrix_3,Mean_3] = Gaussians()
    
    [Gaussian_Confusion, kMeans_Confusion] = confusion()

end

