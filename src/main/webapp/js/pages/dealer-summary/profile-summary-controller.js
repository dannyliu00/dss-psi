/**
 * Created by bericks on 5/13/2014.
 */
(function() {
    var dealerProfileSummary = sellInNamespace('sellIn.pages.dealerProfileSummary');

    function DealerProfileSummaryCtrl ($scope, $routeParams, $location, dealerResource, dealerProfilesResource,
                                       profilePageUrl, lastTab) {
        this.location = $location;

	    var dealer = {dealerId: $routeParams.dealerId, type: $routeParams.type};
	    dealerResource.get(dealer).then(function(returnedDealer) {
	    	$scope.dealer = returnedDealer;
	        });

	    dealerProfilesResource.queryCurrent(dealer).then(function(returnedProfiles) {
	        $scope.profiles = returnedProfiles;
	        });

        $scope.$on('tabClick', function(event, data) {
            if(data === 'current') {
                dealerProfilesResource.queryCurrent(dealer).then(function(profiles) {
                    $scope.profiles = profiles;
                });
            } else {
                dealerProfilesResource.queryHistory(dealer).then(function(profiles) {
                    $scope.profiles = profiles;
                });
            }
        });

        $scope.navigateToProfile = function(dealerId, profileId, type) {
        	lastTab.changeType('');
            var finalUrl = profilePageUrl.replace(':dealerId', dealerId)
                .replace(':profileId', profileId)
                .replace(':type', type);
            $location.path(finalUrl);
            };
    }

    dealerProfileSummary.DealerProfileSummaryCtrl = DealerProfileSummaryCtrl;
}());
