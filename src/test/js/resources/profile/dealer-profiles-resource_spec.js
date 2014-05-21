describe('DealerProfilesResource', function() {
    var httpBackend;

    beforeEach(function() {
        angular.mock.module('sellIn.resources.dealerProfiles');

        angular.mock.inject(function($injector) {
            httpBackend = $injector.get('$httpBackend');
        });
    });

    afterEach(function() {
        httpBackend.verifyNoOutstandingExpectation();
        httpBackend.verifyNoOutstandingRequest();
    });

    describe('query', function() {
        it('returns a promise with a list of profiles for a specified dealer', inject(function(dealerProfilesResource, profilesUrl) {
            var id = 111;
            var dealer = {dealerId: id};
            var expectedRequest = profilesUrl.replace(':dealerId', id).replace('/:profileId', '');
            var expectedProfiles = [{ name: 'U.T. ATV Profile' }, { name: 'U.T. RZR Profile' }];

            httpBackend.when('GET', expectedRequest).respond(expectedProfiles);
            httpBackend.expectGET(expectedRequest);

            var promise = dealerProfilesResource.query(dealer);

            promise.then(function(actualProfiles) {
                expect(actualProfiles.length).toEqual(expectedProfiles.length);
            });

            httpBackend.flush();
        }));
    });

    describe('get', function() {
        it('returns a promise with a single profile of a specified dealer', inject(function(dealerProfilesResource, profilesUrl) {
            var dealerId = 111;
            var profileId = 999;
            var profile = {dealerId: dealerId, profileId: profileId};
            var expectedRequest = profilesUrl.replace(':dealerId', dealerId).replace(':profileId', profileId);
            var expectedProfile = { name: 'U.T. Victory Profile'};

            httpBackend.when('GET', expectedRequest).respond(expectedProfile);
            httpBackend.expectGET(expectedRequest);

            var promise = dealerProfilesResource.get(profile);

            promise.then(function(actualProfile) {
                expect(actualProfile.name).toEqual(expectedProfile.name);
            });

            httpBackend.flush();
        }));
    });

});
