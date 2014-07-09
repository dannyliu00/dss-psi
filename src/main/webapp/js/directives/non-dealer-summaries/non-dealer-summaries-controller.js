(function() {
	var nonDealerSummaries = sellInNamespace('sellIn.directives.nondealersummaries');

    function NonDealerSummariesController ($scope, DTOptionsBuilder, dealerResource) {
    	 	
        $scope.dtOptions = DTOptionsBuilder.newOptions()
	        .withPaginationType('full_numbers')
	        .withDisplayLength(25)
	        .withBootstrap();
       
//        var dealer = {dealerId: $routeParams.dealerId};
//	    dealerResource.get(dealer).then(function(returnedDealer) {
//	    	$scope.dealer = returnedDealer;
//	        });
        
        $scope.lightColor = function(status) {
        	if(status === true){
        		return 'green-light';
        	}else{
        		return 'red-light';
        	}
        };
	       
    }

    nonDealerSummaries.NonDealerSummariesController = NonDealerSummariesController;
})();