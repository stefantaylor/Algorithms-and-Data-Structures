function [] = plots()
    load data_900.mat;
    load true_900.mat;
    a = data_900;
    [gcluster,kcluster] = trueclusters();
    gcluster1 = [];
    gcluster2 = [];
    gcluster3 = [];
    kcluster1 = [];
    kcluster2 = [];
    kcluster3 = [];
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
    figure;
    scatter3(gcluster1(:,1),gcluster1(:,2),gcluster1(:,3),'filled');
    hold on;
    scatter3(gcluster2(:,1),gcluster2(:,2),gcluster2(:,3),'filled');
    hold on;
    scatter3(gcluster3(:,1),gcluster3(:,2),gcluster3(:,3),'filled');
    hold off;
    figure;
    plot(sumsquarederrors);
    figure;
    scatter3(kcluster1(:,1),kcluster1(:,2),kcluster1(:,3),'filled');
    hold on;
    scatter3(kcluster2(:,1),kcluster2(:,2),kcluster2(:,3),'filled');
    hold on;
    scatter3(kcluster3(:,1),kcluster3(:,2),kcluster3(:,3),'filled');
    hold off;

    
    
    
    
    %scatter3(data_900(:,1),data_900(:,2),data_900(:,3),20,gcluster)
    
end