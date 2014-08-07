(function() {
    var mainButton = sellInNamespace('sellIn.directives.mainbutton');

    function MainButtonDirectiveController($scope, $location, $modal, dealerSummaryPageUrl, lastTab) {
    	
        var buildUrl = function() {
            return dealerSummaryPageUrl
                .replace(':type', $scope.profile.typeCode)
                .replace(':status', lastTab.profilesTab);
        };

        var navigateTo = function(url) {
            $location.path(url);
        };
        
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
        }
       
        function autoFillATV() {
               for(var i=0; i < $scope.orderSegments.length; i++) {
                   $scope.orderSegments[i].actual= $scope.orderSegments[i].recommended;
               }
       }
    
        $scope.toSummary = function() {
        	
            lastTab.changeProductTab($scope.profile.typeCode);
            
            if(!$scope.isDirty()) {
                var url = buildUrl();
                navigateTo(url);
            } else {
                openSaveDialog();
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

            modalInstance.result.then(function () {
                var url = buildUrl();
                navigateTo(url);
            }, function () {
                console.log('Modal dismissed at: ' + new Date());
            });
        }
        
        $scope.saveQuantities = function() {
        	if($scope.isDirty()) {
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
            		},
            		resetChanges: function() {
            			return $scope.resetChanges;
            		}
                }
            });

            modalInstance.result.then(function () {
            }, function () {
                console.log('Modal dismissed at: ' + new Date());
            });
        }

        function openSubmitDialog() {
        	
        	var dealer = "dealer";

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
            		},
            		level: function() {
            			return dealer;
            		},
            		confirm: function() {
            			return $scope.profile.dealerEmail;
            		}
                }
            });

            modalInstance.result.then(function () {
                var url = buildUrl();
                navigateTo(url);
            }, function () {
                console.log('Modal dismissed at: ' + new Date());
            });
        }

        $scope.submitRequests = function() {
        	if($scope.profile.dealerEmail == null || $scope.profile.dealerEmail === '') {
        		openEmailRequiredDialog();
        	}
            else if(angular.element('.compliant').hasClass('noncompliant') || angular.element('.compliant').hasClass('noncomplianttotal')) {
                openReasonDialog();
            } else {
                openSubmitDialog();
            }
        };
        
        function openReasonDialog() {
        	
        	var dealerReason = "dealerReason";
        	var modalInstance = $modal.open({
				templateUrl: 'js/directives/modal/reason-modal-template.html',
				controller: 'reasonModalController',
				size: 'sm',
                resolve: {
                    orderSegments: function () {
                        return $scope.orderSegments;
                    },
            		profile: function() {
            			return $scope.profile;
            		},
            		caption: function() {
            			return dealerReason;
            		},
            		confirm: function() {
            			return $scope.profile.dealerEmail;
            		}
                }
			});

        	modalInstance.result.then(function () {
                var url = buildUrl();
                navigateTo(url);
            }, function () {
                console.log('Modal dismissed at: ' + new Date());
            });
		}
        
        function openEmailRequiredDialog() {
        	
        	var modalInstance = $modal.open({
				templateUrl: 'js/directives/modal/email-required-modal-template.html',
				controller: 'emailRequiredController',
				size: 'sm'
			});

        	modalInstance.result.then(function () {
                console.log('Modal dismissed at: ' + new Date());
            });
		}
    }

    mainButton.MainButtonDirectiveController = MainButtonDirectiveController;
})();