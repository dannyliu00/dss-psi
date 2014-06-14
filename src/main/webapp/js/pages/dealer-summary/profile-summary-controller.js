/**
 * Created by bericks on 5/13/2014.
 */
(function() {
    var dealerProfileSummary = sellInNamespace('sellIn.pages.dealerProfileSummary');

    function DealerProfileSummaryCtrl ($scope, $routeParams, $location, dealerResource, dealerProfilesResource,
                                       profilePageUrl,  DTOptionsBuilder) {
        this.location = $location;
            
	    var dealer = {dealerId: $routeParams.dealerId};
	    dealerResource.get(dealer).then(function(returnedDealer) {
	    	$scope.dealer = returnedDealer;
	        });

	    dealerProfilesResource.query(dealer).then(function(returnedProfiles) {
	        $scope.profiles = returnedProfiles;
	        });
	    
        $scope.navigateToProfile = function(dealerId, profileId, type) {
            var finalUrl = profilePageUrl.replace(':dealerId', dealerId)
                .replace(':profileId', profileId)
                .replace(':type', type);
            $location.path(finalUrl);
        };
        
        $scope.dtOptions = DTOptionsBuilder.newOptions()
	        .withPaginationType('full_numbers')
	        .withDisplayLength(10)
	        .withBootstrap();
    }

    dealerProfileSummary.DealerProfileSummaryCtrl = DealerProfileSummaryCtrl;
}());