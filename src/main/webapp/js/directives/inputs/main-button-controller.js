(function() {
    var mainButton = sellInNamespace('sellIn.directives.mainbutton');

    function MainButtonDirectiveController($scope, $location, $modal, dealerSummaryPageUrl) {
    	
        $scope.buttonCaptionFill = function() {
        	var buttonCaption;
        	($scope.profile.type === "motorcycle"? buttonCaption = "Auto-fill with Recommendations" : buttonCaption = "Auto-fill with Targets");
        	
        	return buttonCaption;
        };
             
        $scope.autoFill = function(){
        	($scope.profile.type === "motorcycle" ? autoFillVic() : autoFillATV());
        };
        
        function autoFillVic(){
            for(var i=0; i < $scope.orderSegments.length; i++) {
           	    $scope.orderSegments[i].actual = $scope.orderSegments[i].recommended;
            }
            $scope.sumActualValues();
        }
       
        function autoFillATV() {
               for(var i=0; i < $scope.orderSegments.length; i++) {
                   $scope.orderSegments[i].actual= $scope.orderSegments[i].recommended;
               }
           $scope.getActualGrandTotal();
       }
    
        $scope.toSummary = function(dealerId) {
            var changes = $scope.dirtyIndicator;

            switch(changes) {
                case 0:
                    var finalUrl = dealerSummaryPageUrl.replace(':dealerId', dealerId);
                    $location.path(finalUrl);
                    break;
                case 1:
                    var finalUrl = dealerSummaryPageUrl.replace(':dealerId', dealerId);
                    $location.path(finalUrl);
                    break;
                default:
                    openSaveDialog();
                    break;
            }
        };

        function openSaveDialog () {

            var modalInstance = $modal.open({
                templateUrl: 'js/directives/modal/unsaved-changes-modal-template.html',
                controller: 'unsavedChangesController',
                size: 'sm',
                resolve: {
                    orderSegments: function () {
                        return $scope.orderSegments;
                    },
            		profile: function() {
            			return $scope.profile;
            		},
                    role: function() {
                        return $scope.role;
                    }
                }
            });

            modalInstance.result.then(function (dealerId) {
                var finalUrl = dealerSummaryPageUrl.replace(':dealerId', $scope.dealer.dealerId);
                $location.path(finalUrl);
            }, function () {
                console.log('Modal dismissed at: ' + new Date());
            });
        }
        
        $scope.saveQuantities = function() {
        	if($scope.dirtyIndicator > 1) {
        		openSaveQuantitiesDialog();
        	}
        };

        function openSaveQuantitiesDialog() {

            var modalInstance = $modal.open({
                templateUrl: 'js/directives/modal/save-quantities-modal-template.html',
                controller: 'saveQuantitiesController',
                size: 'sm',
                resolve: {
                    orderSegments: function () {
                        return $scope.orderSegments;
                    },
            		profile: function() {
            			return $scope.profile;
            		}
                }
            });

            modalInstance.result.then(function () {
                $scope.dirtyIndicator = 1;
            }, function () {
                console.log('Modal dismissed at: ' + new Date());
            });
        }

        function openSubmitDialog() {

            var modalInstance = $modal.open({
                templateUrl: 'js/directives/modal/submit-modal-template.html',
                controller: 'submitController',
                size: 'sm',
                resolve: {
                    orderSegments: function () {
                        return $scope.orderSegments;
                    },
            		profile: function() {
            			return $scope.profile;
            		}
                }
            });

            modalInstance.result.then(function () {
                var finalUrl = dealerSummaryPageUrl.replace(':dealerId', $scope.dealer.dealerId);
                $location.path(finalUrl);
            }, function () {
                console.log('Modal dismissed at: ' + new Date());
            });
        }

        $scope.submitRequests = function() {
            if(angular.element('.compliant').hasClass('noncompliant') || angular.element('.compliant').hasClass('noncomplianttotal')) {
                openReasonDialog();
            } else {
                openSubmitDialog();
            }
        };
        
        function openReasonDialog() {
        	
        	var modalInstance = $modal.open({
				templateUrl: 'js/directives/modal/reason-modal-template.html',
				controller: 'reasonModalController',
				size: 'sm',
                resolve: {
                    data: function () {
                        return $scope.orderSegments;
                    },
            		profile: function() {
            			return $scope.profile;
            		},
            		caption: function() {
            			return 'dealerReason';
            		}
                }
			});

        	modalInstance.result.then(function () {
                var finalUrl = dealerSummaryPageUrl.replace(':dealerId', $scope.dealer.dealerId);
                $location.path(finalUrl);
            }, function () {
                console.log('Modal dismissed at: ' + new Date());
            });
		}
    }

    mainButton.MainButtonDirectiveController = MainButtonDirectiveController;
})();