(function () {
    var mainButton = sellInNamespace('sellIn.directives.mainbutton');

    function MainButtonDirectiveController($scope, $location, $modal, dealerSummaryPageUrl, lastTab) {

        var buildUrl = function () {
            return dealerSummaryPageUrl
                .replace(':type', $scope.profile.typeCode)
                .replace(':status', lastTab.profilesTab);
        };

        var navigateTo = function (url) {
            $location.path(url);
        };

        $scope.buttonCaptionFill = function () {
            return $scope.profile.type === 'motorcycle' ? 'Reset to Recommendations' : 'Reset to Targets';
        };

        $scope.autoFill = function () {
            for (var i = 0, j = $scope.orderSegments.length; i < j; i++) {
                $scope.orderSegments[i].actual = $scope.orderSegments[i].recommended;
            }
        };

        $scope.toSummary = function () {

            lastTab.changeProductTab($scope.profile.typeCode);

            if (!$scope.isDirty()) {
                var url = buildUrl();
                navigateTo(url);
            } else {
                openSaveDialog();
            }
        };

        function openSaveDialog() {

            var modalInstance = $modal.open({
                templateUrl: 'js/directives/modal/unsaved-changes-modal-template.html',
                controller: 'unsavedChangesController',
                size: 'sm',
                resolve: {
                    orderSegments: function () {
                        return $scope.orderSegments;
                    },
                    profile: function () {
                        return $scope.profile;
                    },
                    role: function () {
                        return $scope.role;
                    }
                }
            });

            modalInstance.result.then(function (profile) {
                if (profile.successful === true) {
                    var url = buildUrl();
                    navigateTo(url);
                } else {
                    openResultsDialog(profile);
                }
            }, function () {
                // no-op
            });
        }

        $scope.saveQuantities = function () {
            if ($scope.isDirty()) {
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
                    profile: function () {
                        return $scope.profile;
                    },
                    resetChanges: function () {
                        return $scope.resetChanges;
                    }
                }
            });

            modalInstance.result.then(function (profile) {
                if (profile.successful !== true) {
                    openResultsDialog(profile);
                } else {
                	$scope.profile.orderSegments = profile.orderSegments;
                	$scope.orderSegments = profile.orderSegments;
                	$scope.profile.status = 'IN PROGRESS';
                }
            }, function () {
                // no-op
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
                    profile: function () {
                        return $scope.profile;
                    },
                    level: function () {
                        return dealer;
                    },
                    confirm: function () {
                        return $scope.profile.dealerEmail;
                    }
                }
            });

            modalInstance.result.then(function (profile) {
                if (profile.successful === true) {
                    var url = buildUrl();
                    navigateTo(url);
                } else {
                    openResultsDialog(profile);
                }
            }, function () {
                // no-op
            });
        }

        $scope.submitRequests = function () {
            if ($scope.profile.dealerEmail == null || $scope.profile.dealerEmail === '') {
                openEmailRequiredDialog();
            } else if (angular.element('.compliant').hasClass('noncompliant') || angular.element('.compliant').hasClass('noncomplianttotal')) {
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
                    profile: function () {
                        return $scope.profile;
                    },
                    caption: function () {
                        return dealerReason;
                    },
                    confirm: function () {
                        return $scope.profile.dealerEmail;
                    }
                }
            });

            modalInstance.result.then(function (profile) {
                if (profile.successful === true) {
                    var url = buildUrl();
                    navigateTo(url);
                } else {
                    openResultsDialog(profile);
                }
            }, function () {
                // no-op
            });
        }

        function openEmailRequiredDialog() {

            var modalInstance = $modal.open({
                templateUrl: 'js/directives/modal/email-required-modal-template.html',
                controller: 'emailRequiredController',
                size: 'sm'
            });

            modalInstance.result.then(function () {
            	angular.element('.email-entry').trigger('focus');
            });
        }

        function openResultsDialog(profile) {

            var modalInstance = $modal.open({
                templateUrl: 'js/directives/modal/results-modal-template.html',
                controller: 'resultsModalController',
                size: 'sm',
                resolve: {
                    profile: function () {
                        return profile;
                    }
                }
            });

            modalInstance.result.then(function () {
                if(profile.message === 'The profile status has changed.  Please try again.') {
                    var url = buildUrl();
                    navigateTo(url);
                }
            });
        }
    }

    mainButton.MainButtonDirectiveController = MainButtonDirectiveController;
})();