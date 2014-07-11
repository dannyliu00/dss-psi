(function() {
    var nonDealerSummary = sellInNamespace('sellIn.pages.nondealersummary');

    function NonDealerSummaryController($scope, $routeParams, $location, dsmProfilesResource, appRoleResource, rsmProfilesResource, profilePageUrl) {
    	
    	appRoleResource.get().then(function(role) {
            $scope.role = role;
            $scope.profiles = $scope.tabContent();
   		});
    	
    	$scope.tabContent = function(activeContent) {

            if(activeContent != null){
                $scope.activeTabFilter = activeContent;
            } else {
                $scope.activeTabFilter = "2";
            }

            var userId = $routeParams.id;
            var type = $scope.activeTabFilter;

            if($scope.role.dsm === true) {
                var dsm = {dsmId: userId, type: type};
                dsmProfilesResource.query(dsm).then(function(returnedProfiles) {
                    $scope.profiles = returnedProfiles;
                    });
            } else if($scope.role.rsm === true) {
                var rsm = {rsmId: userId, type: type};
                rsmProfilesResource.query(rsm).then(function(returnedProfiles){
                    $scope.profiles = returnedProfiles;
                });
            }
        };
         
         $scope.navigateToNonDealerProfile = function(dealerId, profileId, type) {
             var finalUrl = profilePageUrl.replace(':dealerId', dealerId)
                 .replace(':profileId', profileId)
                 .replace(':type', type);
             $location.path(finalUrl);
             };
    }

    nonDealerSummary.NonDealerSummaryController = NonDealerSummaryController;
})();