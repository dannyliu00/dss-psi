(function() {
    var saveQuantities = sellInNamespace('sellIn.directives.savequantities');

    function SaveQuantitiesController($scope, $modalInstance, dealerProfileDetailsResource, orderSegments) {
    	
        $scope.saveChanges = function () {
        	dealerProfileDetailsResource.save(orderSegments)
                .then(function(returnedOrderSegments) {
                    $scope.orderSegments = returnedOrderSegments;
                    $modalInstance.close();
                });
        };
        
        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };
    }

    saveQuantities.SaveQuantitiesController = SaveQuantitiesController;
})();