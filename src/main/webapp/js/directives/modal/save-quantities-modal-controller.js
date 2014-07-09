(function() {
    var saveQuantities = sellInNamespace('sellIn.directives.savequantities');

    function SaveQuantitiesController($scope, $modalInstance, dealerProfileResource, orderSegments) {
    	
        $scope.saveChanges = function () {
        	
        	if(angular.element('input').hasClass('noncompliant')) {
                $scope.profile.nonCompliant = true;
            } else {
                $scope.profile.nonCompliant = false;
            }
        	
        	var os = {nonCompliant: $scope.profile.nonCompliant,orderSegments: orderSegments};
        	
        	dealerProfileResource.save(os)
                .then(function(returnedos) {
                    $scope.orderSegments = returnedos.orderSegments;
                    $scope.profile.nonCompliant = returnedos.nonCompliant
                    $modalInstance.close();
                });
        };
        
        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };
    }

    saveQuantities.SaveQuantitiesController = SaveQuantitiesController;
})();