(function() {
    var nonDealerSummary = sellInNamespace('sellIn.pages.nondealersummary');

    function NonDealerSummaryController($scope, dsmUrl, dsmProfilesResource, $routeParams, appRoleResource) {
     	
    	appRoleResource.get().then(function(role) {
            $scope.role = role;
    		});
	    	
//    	var dsmId = $routeParams.id;
	    	
//	    var startType = "2";
	    
//    	var dsm = {dsmId: dsmId,type: startType};
	    	
//    	dsmProfilesResource.query(dsm).then(function(returnedProfiles) {
        
// 	        });
    
    	 $scope.tabContent = function(activeContent) {
         	
    		var retProfiles = {};
    		 
         	if(activeContent != null){
         		$scope.activeTabFilter = activeContent;
         	} else {
         		$scope.activeTabFilter = "2";
         	}
         	
         	var dsmId = $routeParams.id;
         	
         	var type = $scope.activeTabFilter;
         	
         	var dsm = {dsmId: dsmId,type: type};
         	
         	dsmProfilesResource.query(dsm).then(function(returnedProfiles) {
      	        retProfiles = returnedProfiles;
      	        });
         		return retProfiles;
         };
         
         $scope.profiles = $scope.tabContent();
         
//         $scope.navigateToProfile = function(dealerId, profileId, type) {
//             var finalUrl = profilePageUrl.replace(':dealerId', dealerId)
//                 .replace(':profileId', profileId)
//                 .replace(':type', type);
//             $location.path(finalUrl);
//             };
    }

    nonDealerSummary.NonDealerSummaryController = NonDealerSummaryController;
})();