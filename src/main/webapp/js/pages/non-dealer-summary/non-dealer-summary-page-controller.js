(function() {
    var nonDealerSummary = sellInNamespace('sellIn.pages.nondealersummary');

    function NonDealerSummaryController($scope, $routeParams, $location, dsmProfilesResource, appRoleResource, rsmProfilesResource, profilePageUrl, productTabs, lastTab) {
    	
    	var currentTabs = [];
    	
    	appRoleResource.get().then(function(role) {
    		for(var i = 0; i < productTabs.length; i++) {
        		if(productTabs[i].content === '2' && role.sessionDetail.ATV === 'Y') {
        			currentTabs.push(productTabs[i]);
    			} else if(productTabs[i].content === '5' && role.sessionDetail.VIC === 'Y') {
    				currentTabs.push(productTabs[i]);
    			} else if(productTabs[i].content === '6' && role.sessionDetail.RGR === 'Y') {
    				currentTabs.push(productTabs[i]);
    			} else if(productTabs[i].content === 'Z' && role.sessionDetail.RZR === 'Y') {
    				currentTabs.push(productTabs[i]);
    			} else if(productTabs[i].content === 'F' && role.sessionDetail.IND === 'Y') {
    				currentTabs.push(productTabs[i]);
    			}
        		$scope.productTabs = currentTabs;
    		}
            $scope.role = role;
            $scope.profiles = $scope.tabContent();
   		});
    	
    	$scope.tabContent = function(activeContent) {

            if(activeContent != null){
                $scope.activeTabFilter = activeContent;
            } else if(lastTab.tab !== '') {
            	$scope.activeTabFilter = lastTab.tab;
            } else {
                $scope.activeTabFilter = $scope.productTabs[0].content;
            }
            
            for(var k = 0; k < $scope.productTabs.length; k++) {
            	if($scope.productTabs[k].content === $scope.activeTabFilter) {
            		$scope.productTabs[k].isActive === true;
            	} else {
            		$scope.productTabs[k].isActive === false;
            	}
            }
            var userId = $routeParams.id;
            var type = $scope.activeTabFilter;

            if($scope.role.dsm === true) {
                var dsm = {dsmId: userId, type: type};
                dsmProfilesResource.queryCurrent(dsm).then(function(returnedProfiles) {
                    $scope.profiles = returnedProfiles;
                    });
            } else if($scope.role.rsm === true) {
                var rsm = {rsmId: userId, type: type};
                rsmProfilesResource.queryCurrent(rsm).then(function(returnedProfiles){
                    $scope.profiles = returnedProfiles;
                });
            }
        };
         
         $scope.navigateToNonDealerProfile = function(dealerId, profileId, type) {
        	 lastTab.changeType('');
             var finalUrl = profilePageUrl.replace(':dealerId', dealerId)
                 .replace(':profileId', profileId)
                 .replace(':type', type);
             $location.path(finalUrl);
             };

    }

    nonDealerSummary.NonDealerSummaryController = NonDealerSummaryController;
})();
	
		
