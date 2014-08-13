(function () {
    var dealerProfiles = sellInNamespace('sellIn.directives.profiles');

    describe('DealerProfileController', function () {
        var scope, DTOptionsBuilder, DTOptions, routeParams, dealerResource, dealerProfileResource;
        var expectedDealerId, expectedProfileId, appRoleResource;
        var expectedDealerDeferred, expectedProfileDeferred;
        var expectedRoleDeferred, expectedType, lastTab, blockUI, currentDealer;
        var ctrl;

        beforeEach(function () {
            angular.mock.module('sellIn.directives.profiles');

            angular.mock.module(function ($provide) {
                expectedDealerId = 9999;
                expectedProfileId = 1234;
                expectedType = 'T';
                routeParams = {profileId: expectedProfileId, type: expectedType};

                appRoleResource = jasmine.createSpyObj('appRoleResource', ['get']);
                $provide.decorator('appRoleResource', [function () {
                    return appRoleResource;
                }]);
                dealerResource = jasmine.createSpyObj('dealerResource', ['get', 'getNonDealer']);
                $provide.decorator('dealerResource', [function () {
                    return dealerResource;
                }]);
                dealerProfileResource = jasmine.createSpyObj('dealerProfileResource', ['get', 'nonDealer']);
                $provide.decorator('dealerProfileResource', [function () {
                    return dealerProfileResource;
                }]);
                lastTab = jasmine.createSpyObj('lastTab', ['changeProductTab', 'changeProfilesTab']);
                $provide.decorator('lastTab', [function () {
                    return lastTab;
                }]);
                blockUI = jasmine.createSpyObj('blockUI', ['start', 'stop', 'instances']);
                $provide.decorator('blockUI', [function () {
                    return blockUI;
                }]);
                currentDealer = jasmine.createSpyObj('currentDealer', ['changeDealerId']);
                currentDealer.dealerId = expectedDealerId;
                $provide.decorator('currentDealer', [function () {
                    return currentDealer;
                }]);
            });

            DTOptionsBuilder = jasmine.createSpyObj('DTOptionsBuilder', ['newOptions']);

            DTOptions = jasmine.createSpyObj('DTOptions', ['withPaginationType', 'withDisplayLength', 'withBootstrap']);
            DTOptionsBuilder.newOptions.andReturn(DTOptions);
            DTOptions.withPaginationType.andReturn(DTOptions);
            DTOptions.withDisplayLength.andReturn(DTOptions);
        });

        beforeEach(inject(function ($q, $rootScope, dealerResource, dealerProfileResource, appRoleResource) {
            expectedRoleDeferred = $q.defer();
            appRoleResource.get.andReturn(expectedRoleDeferred.promise);

            expectedDealerDeferred = $q.defer();
            dealerResource.get.andReturn(expectedDealerDeferred.promise);
            dealerResource.getNonDealer.andReturn(expectedDealerDeferred.promise);

            expectedProfileDeferred = $q.defer();
            dealerProfileResource.get.andReturn(expectedProfileDeferred.promise);
            dealerProfileResource.nonDealer.andReturn(expectedProfileDeferred.promise);

            scope = $rootScope.$new();
        }));

        describe('constructor', function () {
            it('initializes dealer on scope for dealer role',
                inject(function ($rootScope, dealerResource, dealerProfileResource, orderSegmentResourceMapper, appRoleResource, lastTab, blockUI, currentDealer) {

                    var expectedRole = {
                        name: 'U.T. Role',
                        roleId: 5,
                        dealer: true,
                        rsm: false,
                        dsm: false
                    };
                    var expectedDealer = {
                        name: 'U.T. Dealer',
                        dealerId: expectedDealerId
                    };
                    var expectedProfile = {
                        name: 'U.T. Profile',
                        type: 'motorcycle',
                        segments: [{
                            name: 'U.T. Segment Name',
                            subSegments: ['U.T. OrderSegment Name']
                        }],
                        orderSegments: [{
                            osName: 'U.T. OrderSegment Name',
                            periodCode: 'U.T. Code'
                        }],
                        periods: [{
                            name: 'U.T. Period',
                            code: 'U.T. Code'
                        }]
                    };

                    expectedRoleDeferred.resolve(expectedRole);
                    expectedDealerDeferred.resolve(expectedDealer);
                    expectedProfileDeferred.resolve(expectedProfile);

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

                    var expectedDealerToRetrieve = {type: expectedType};
                    var expectedProfileToRetrieve = {profileId: expectedProfileId};

                    expect(appRoleResource.get).toHaveBeenCalled();
                    expect(dealerResource.get).toHaveBeenCalledWith(expectedDealerToRetrieve);
                    expect(dealerProfileResource.get).toHaveBeenCalledWith(expectedProfileToRetrieve);
                    expect(blockUI.start).toHaveBeenCalled();
                    expect(blockUI.stop).toHaveBeenCalled();
                    expect(DTOptionsBuilder.newOptions).toHaveBeenCalled();
                    expect(DTOptions.withPaginationType).toHaveBeenCalled();
                    expect(DTOptions.withDisplayLength).toHaveBeenCalled();
                    expect(DTOptions.withBootstrap).toHaveBeenCalled();
                    expect(currentDealer.dealerId).toBeDefined();
                }));
        });

        describe('constructor', function () {
            it('initializes dealer on scope for non-dealer role',
                inject(function ($rootScope, dealerResource, dealerProfileResource, orderSegmentResourceMapper, appRoleResource, lastTab, blockUI, currentDealer) {

                    var expectedRole = {
                        name: 'U.T. Role',
                        roleId: 5,
                        dealer: false,
                        rsm: false,
                        dsm: true
                    };

                    var expectedDealer = {
                        name: 'U.T. Dealer',
                        dealerId: expectedDealerId
                    };
                    var expectedProfile = {
                        name: 'U.T. Profile',
                        type: 'motorcycle',
                        segments: [{
                            name: 'U.T. Segment Name',
                            subSegments: ['U.T. OrderSegment Name']
                        }],
                        orderSegments: [{
                            osName: 'U.T. OrderSegment Name',
                            periodCode: 'U.T. Code'
                        }],
                        periods: [{
                            name: 'U.T. Period',
                            code: 'U.T. Code'
                        }]
                    };

                    expectedRoleDeferred.resolve(expectedRole);
                    expectedDealerDeferred.resolve(expectedDealer);
                    expectedProfileDeferred.resolve(expectedProfile);

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

                    var expectedDealerToRetrieve = {type: expectedType, dealerId: expectedDealerId};
                    var expectedProfileToRetrieve = {profileId: expectedProfileId, dealerId: expectedDealerId};

                    expect(appRoleResource.get).toHaveBeenCalled();
                    expect(dealerResource.getNonDealer).toHaveBeenCalledWith(expectedDealerToRetrieve);
                    expect(dealerProfileResource.nonDealer).toHaveBeenCalledWith(expectedProfileToRetrieve);
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
