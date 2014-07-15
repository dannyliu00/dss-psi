(function() {
    var saveQuantities = sellInNamespace('sellIn.directives.savequantities');

    function SaveQuantitiesController($scope, $modalInstance, dealerProfileResource, orderSegments, profile) {
    	
        $scope.saveChanges = function () {
        	
        	if(angular.element('.compliant').hasClass('noncompliant')) {
                profile.nonCompliant = true;
            } else {
                profile.nonCompliant = false;
            }
        	
        	var os = {nonCompliant: profile.nonCompliant,orderSegments: orderSegments};
        	
        	dealerProfileResource.save(os)
                .then(function(returnedos) {
                    orderSegments = returnedos.orderSegments;
                    profile.nonCompliant = returnedos.nonCompliant
                    $modalInstance.close();
                });
        };
        
        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };
    }

    saveQuantities.SaveQuantitiesController = SaveQuantitiesController;
})();