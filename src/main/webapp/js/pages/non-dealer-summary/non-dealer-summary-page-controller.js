(function() {
    var nonDealerSummary = sellInNamespace('sellIn.pages.nondealersummary');

    function NonDealerSummaryController($scope, dsmUrl, dsmProfilesResource, $routeParams, appRoleResource) {
    	
    	var dsmId = {dsmId: $routeParams.id};
    	
    	appRoleResource.get().then(function(role) {
            $scope.role = role;
    		});
    	
    	dsmProfilesResource.query(dsmId).then(function(returnedProfiles) {
 	        $scope.profiles = returnedProfiles;
 	        });
 	    
//         $scope.navigateToProfile = function(dealerId, profileId, type) {
//             var finalUrl = profilePageUrl.replace(':dealerId', dealerId)
//                 .replace(':profileId', profileId)
//                 .replace(':type', type);
//             $location.path(finalUrl);
//             };
    }

    nonDealerSummary.NonDealerSummaryController = NonDealerSummaryController;
})();