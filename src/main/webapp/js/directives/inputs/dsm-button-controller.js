(function () {
    var dsmButton = sellInNamespace('sellIn.directives.dsmbutton');

    function DsmButtonController($scope, $location, $modal, dsmUrl, lastTab,translationPSI) {
        var caption = '';
        var changeCaption = 0;
        var dsmButtonCaption = "";

        var buildUrl = function () {
            return dsmUrl
                .replace(':type', $scope.profile.typeCode)
                .replace(':status', lastTab.profilesTab);
        };

        $scope.dsmButtonCaptionFill = function () {
            if (angular.element('.compliant').hasClass('noncompliant') || angular.element('.compliant').hasClass('noncomplianttotal')) {
                dsmButtonCaption = translationPSI.getString("Submit for Exception");
            } else if (isChanged() || angular.element('input').hasClass('noncompliant')) {
                dsmButtonCaption = translationPSI.getString("Approve with Changes");
            } else {
                dsmButtonCaption = translationPSI.getString("Approve as Requested");
            }
            return  dsmButtonCaption;
        };


        $scope.dsmToSummary = function (dealerId) {
            lastTab.changeProductTab($scope.profile.typeCode);

            if (isChanged() && $scope.isDirty()) {
                openSaveDialog();
            } else {
                var finalDsmUrl = buildUrl();
                $location.path(finalDsmUrl);
            }
        };

        function openSaveDialog() {

            var modalInstance = $modal.open({
                templateUrl: 'js/directives/modal/unsaved-changes-modal-template.html',
                controller: 'unsavedChangesController',
                size: 'sm',
                resolve: {
                    role: function () {
                        return $scope.role;
                    },
                    orderSegments: function () {
                        return $scope.orderSegments;
                    },
                    profile: function () {
                        return $scope.profile;
                    }
                }
            });

            modalInstance.result.then(function (success) {
                if (success === true) {
                    var finalDsmUrl = buildUrl();
                    $location.path(finalDsmUrl);
                } else {
                    openResultsDialog();
                }
            }, function () {
                //no-op
            });
        }

        $scope.approveSubmit = function () {
            changeCaption = 0;
            if (dsmButtonCaption === "Approve as Requested") {
                openSubmitDialog();
            } else {
                openReasonDialog();
            }
        };

        $scope.sendBack = function () {
            changeCaption = 1;
            openReasonDialog();
        };

        function openReasonDialog() {

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
                        if (changeCaption === 1) {
                            caption = 'sendBack';
                        } else {
                            caption = dsmButtonCaption;
                        }
                        return caption;
                    },
                    confirm: function () {
                        return '';
                    }
                }
            });

            modalInstance.result.then(function (profile) {
                if (profile.successful === true) {
                    var finalDsmUrl = buildUrl();
                    $location.path(finalDsmUrl);
                } else {
                    openResultsDialog(profile);
                }
            }, function () {
                // no-op
            });
        }

        function openSubmitDialog() {

            var dsm = "dsm";

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
                        return dsm;
                    },
                    confirm: function () {
                        return '';
                    }

                }
            });

            modalInstance.result.then(function (profile) {
                if (profile.successful === true) {
                    var finalDsmUrl = buildUrl();
                    $location.path(finalDsmUrl);
                } else {
                    openResultsDialog(profile);
                }
            }, function () {
                // no-op
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
                    $location.path(url);
                }
            });
        }

        function isChanged() {
            for (var i = 0, j = $scope.orderSegments.length; i < j; i++) {
                if ($scope.orderSegments[i].actual !== parseInt($scope.orderSegments[i].dsmQty)) {
                    return true;
                }
            }
            return false;
        }
    }

    dsmButton.DsmButtonController = DsmButtonController;
})();