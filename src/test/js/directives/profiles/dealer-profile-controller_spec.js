(function () {
    var dealerProfiles = sellInNamespace('sellIn.directives.profiles');

    describe('DealerProfileController', function () {
        var scope, DTOptionsBuilder, DTOptions, routeParams, dealerResource, dealerProfileResource;
        var expectedDealerId, expectedProfileId, appRoleResource, expectedNonDealerProfileDeferred;
        var expectedDealerDeferred, expectedDealer, expectedProfileDeferred, expectedProfile;
        var expectedRoleDeferred, expectedRole, expectedType, lastTab, blockUI, currentDealer;
        var ctrl;

        beforeEach(function () {
            angular.mock.module('sellIn.directives.profiles');

            angular.mock.module(function ($provide) {
                appRoleResource = jasmine.createSpyObj('appRoleResource', ['get']);
                $provide.decorator('appRoleResource', [function () {
                    return appRoleResource;
                }]);
                dealerResource = jasmine.createSpyObj('dealerResource', ['get']);
                $provide.decorator('dealerResource', [function () {
                    return dealerResource;
                }]);
                dealerProfileResource = jasmine.createSpyObj('dealerProfileResource', ['get']);
                $provide.decorator('dealerProfileResource', [function () {
                    return dealerProfileResource;
                }]);
                lastTab = jasmine.createSpyObj('lastTab', ['changeProductTab', 'changeProfilesTab']);
                $provide.decorator('lastTab', [function () {
                    return lastTab;
                }]);
                blockUI = jasmine.createSpyObj('blockUI', ['start', 'stop']);
                $provide.decorator('blockUI', [function () {
                    return blockUI;
                }]);
                currentDealer = jasmine.createSpyObj('currentDealer', ['changeDealerId']);
                currentDealer.dealerId = 9999;
                $provide.decorator('currentDealer', [function () {
                    return currentDealer;
                }]);
            });

            expectedProfileId = 1234;
            expectedType = 'T';
            expectedDealerId = 12345;
            routeParams = {profileId: expectedProfileId, type: expectedType};

            DTOptionsBuilder = jasmine.createSpyObj('DTOptionsBuilder', ['newOptions']);

            DTOptions = jasmine.createSpyObj('DTOptions', ['withPaginationType', 'withDisplayLength', 'withBootstrap']);
            DTOptionsBuilder.newOptions.andReturn(DTOptions);
            DTOptions.withPaginationType.andReturn(DTOptions);
            DTOptions.withDisplayLength.andReturn(DTOptions);
        });

        beforeEach(inject(function ($q, $rootScope, dealerResource, dealerProfileResource, appRoleResource) {
            expectedRoleDeferred = $q.defer();
            appRoleResource.get.andReturn(expectedRoleDeferred.promise);
            expectedRole = {
                name: 'U.T. Role',
                roleId: 5
            };
            expectedDealerDeferred = $q.defer();
            dealerResource.get.andReturn(expectedDealerDeferred.promise);
            expectedDealer = {
                name: 'U.T. Dealer',
                dealerId: 999
            };

            expectedProfileDeferred = $q.defer();
            dealerProfileResource.get.andReturn(expectedProfileDeferred.promise);
            expectedProfile = {
                name: 'U.T. Profile',
                segments: [
                    {
                        name: 'U.T. Segment Name',
                        orderSegments: [
                            {
                                name: 'U.T. OrderSegment Name'
                            }
                        ]
                    }
                ]
            };

            scope = $rootScope.$new();
        }));

        describe('constructor', function () {
            it('initializes dealer on scope',
                inject(function ($rootScope, dealerResource, dealerProfileResource, orderSegmentResourceMapper, appRoleResource, lastTab, blockUI, currentDealer) {

                    expectedRoleDeferred.resolve(expectedRole);
                    expectedDealerDeferred.resolve(expectedDealer);

                    ctrl = new dealerProfiles.DealerProfileDirectiveController(
                        scope,
                        DTOptionsBuilder,
                        routeParams,
                        dealerResource,
                        dealerProfileResource,
                        orderSegmentResourceMapper,
                        appRoleResource,
                        lastTab,
                        currentDealer,
                        blockUI);

                    $rootScope.$digest();

                    var expectedDealer = {type: expectedType};
                    var expectedProfile = {profileId: expectedProfileId};
                    expect(dealerResource.get).toHaveBeenCalledWith(expectedDealer);
                    expect(dealerProfileResource.get).toHaveBeenCalledWith(expectedProfile);
                    expect(blockUI.start).toHaveBeenCalled();
                    expect(blockUI.stop).toHaveBeenCalled();
                    expect(DTOptionsBuilder.newOptions).toHaveBeenCalled();
                    expect(DTOptions.withPaginationType).toHaveBeenCalled();
                    expect(DTOptions.withDisplayLength).toHaveBeenCalled();
                    expect(DTOptions.withBootstrap).toHaveBeenCalled();
                    expect(currentDealer.dealerId).toBeDefined();
                }));
        });
    });
})();
