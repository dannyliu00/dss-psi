(function () {
    var rsmButton = sellInNamespace('sellIn.directives.rsmbutton');

    function RsmButtonController($scope, $location, $modal, dsmUrl, lastTab) {
        var caption = '';
        var status = 0;
        var rsmButtonCaption = "";

        var buildUrl = function () {
            return dsmUrl
                .replace(':type', $scope.profile.typeCode)
                .replace(':status', lastTab.profilesTab);
        };

        $scope.rsmButtonCaptionFill = function () {
            if ($scope.profile.typeCode === '5' || $scope.profile.typeCode === 'F') {
                rsmButtonCaption = "Return to DRM";
            } else {
                rsmButtonCaption = "Return to DSM";
            }
            return  rsmButtonCaption;
        };


        $scope.rsmToSummary = function () {
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
                // no-op
            });
        }

        $scope.approveCompliant = function () {
            status = 1;
            openReasonDialog();
        };

        $scope.approveNonCompliant = function () {
            status = 2;
            openReasonDialog();
        };

        $scope.returnDSM = function () {
            status = 3;
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
                        if (status === 1) {
                            caption = 'Approve as Compliant';
                        } else if (status === 2) {
                            caption = 'Approve as Non-Compliant';
                        } else if (status === 3) {
                            caption = 'Return to DSM';
                        }
                        return caption;
                    },
                    confirm: function () {
                        return '';
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
                // no-op
            });
        }

        function openResultsDialog() {

            var modalInstance = $modal.open({
                templateUrl: 'js/directives/modal/results-modal-template.html',
                controller: 'resultsModalController',
                size: 'sm'
            });

            modalInstance.result.then(function () {
                // no-op
            });
        }

        function isChanged() {
            for (var i = 0, j = $scope.orderSegments.length; i < j; i++) {
                if ($scope.orderSegments[i].dsmQty !== parseInt($scope.orderSegments[i].adminQty)) {
                    return true;
                }
            }
            return false;
        }
    }

    rsmButton.RsmButtonController = RsmButtonController;
})();