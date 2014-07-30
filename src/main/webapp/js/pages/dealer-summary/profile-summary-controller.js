/**
 * Created by bericks on 5/13/2014.
 */
(function() {
    var dealerProfileSummary = sellInNamespace('sellIn.pages.dealerProfileSummary');

    function DealerProfileSummaryCtrl ($scope, $routeParams, $location, dealerResource, dealerProfilesResource,
                                       profilePageUrl, dealerSummaryPageUrl, lastTab, blockUI) {

        this.location = $location;

        var loadProfiles = function(status) {
            // Block the user interface
            blockUI.start();

            lastTab.changeProfilesTab(status);

            if(status === 'current') {
                dealerProfilesResource.queryCurrent(dealer).then(function(profiles) {
                    $scope.profiles = profiles;
                });
            } else {
                dealerProfilesResource.queryHistory(dealer).then(function(profiles) {
                    $scope.profiles = profiles;
                });
            }
            // Unblock the user interface
            blockUI.stop();
        };

        var dealer = {dealerId: $routeParams.dealerId, type: $routeParams.type};
	    dealerResource.get(dealer).then(function(returnedDealer) {
	    	$scope.dealer = returnedDealer;
        });

        loadProfiles($routeParams.status);

        $scope.navigateToProfile = function(dealerId, profileId, type) {
        	lastTab.changeProductTab('');
            var finalUrl = profilePageUrl.replace(':dealerId', dealerId)
                .replace(':profileId', profileId)
                .replace(':type', type);
            $location.path(finalUrl);
        };

        $scope.$on('tabClick', function(event, data) {
            lastTab.changeProfilesTab(data);
            var dealerId = $routeParams.dealerId;
            var type = $routeParams.type;
            var status = data;
            var finalUrl = dealerSummaryPageUrl
                .replace(':dealerId', dealerId)
                .replace(':type', type)
                .replace(':status', status);
            $location.path(finalUrl);
        });

    }

    dealerProfileSummary.DealerProfileSummaryCtrl = DealerProfileSummaryCtrl;
}());
