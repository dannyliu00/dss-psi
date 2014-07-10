(function() {
	var nonDealerSummaries = sellInNamespace('sellIn.directives.nondealersummaries');

    function NonDealerSummariesController ($scope, DTOptionsBuilder, dealerResource) {
    	 	
        $scope.dtOptions = DTOptionsBuilder.newOptions()
	        .withPaginationType('full_numbers')
	        .withDisplayLength(25)
	        .withBootstrap();
    }

    nonDealerSummaries.NonDealerSummariesController = NonDealerSummariesController;
})();