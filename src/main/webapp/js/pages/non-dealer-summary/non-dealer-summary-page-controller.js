(function() {
    var nonDealerSummary = sellInNamespace('sellIn.pages.nondealersummary');

    function NonDealerSummaryController($scope, dsmUrl, dsmProfilesResource, $routeParams, appRoleResource) {
     	
    	appRoleResource.get().then(function(role) {
            $scope.role = role;
    		});
    	
    	 $scope.tabContent = function(activeContent) {
    		 
         	if(activeContent != null){
         		$scope.activeTabFilter = activeContent;
         	} else {
         		$scope.activeTabFilter = "2";
         	}
         	
         	var dsmId = $routeParams.id;
         	
         	var type = $scope.activeTabFilter;
         	
         	var dsm = {dsmId: dsmId,type: type};
         	
         	dsmProfilesResource.query(dsm).then(function(returnedProfiles) {
         		$scope.profiles = returnedProfiles;
      	        });
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