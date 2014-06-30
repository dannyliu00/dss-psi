(function() {
	var nonDealerSummaries = sellInNamespace('sellIn.directives.nondealersummaries');

    function NonDealerSummariesController ($scope, DTOptionsBuilder) {
    	 	
        $scope.dtOptions = DTOptionsBuilder.newOptions()
	        .withPaginationType('full_numbers')
	        .withDisplayLength(10)
	        .withBootstrap();

    }

    nonDealerSummaries.NonDealerSummariesController = NonDealerSummariesController;
}());