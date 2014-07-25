(function() {
    var saveQuantities = sellInNamespace('sellIn.directives.savequantities');

    function SaveQuantitiesController($scope, $modalInstance, dealerProfileResource, orderSegments, profile, resetChanges) {
    	
        $scope.saveChanges = function () {
        	
        	if(angular.element('.compliant').hasClass('noncompliant') || angular.element('.compliant').hasClass('noncomplianttotal')) {
                profile.nonCompliant = true;
            } else {
                profile.nonCompliant = false;
            }
        	
        	var os = {nonCompliant: profile.nonCompliant,orderSegments: orderSegments};
        	
        	dealerProfileResource.save(os)
                .then(function(returnedos) {
                    orderSegments = returnedos.orderSegments;
                    profile.nonCompliant = returnedos.nonCompliant;
                    
                    resetChanges();
                    
                    $modalInstance.close();
                });
        };
        
        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };
    }

    saveQuantities.SaveQuantitiesController = SaveQuantitiesController;
})();