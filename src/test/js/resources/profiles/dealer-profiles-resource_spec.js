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

    describe('queryCurrent', function() {
        it('returns a promise with a list of profiles of a specified dealer', inject(function(dealerProfilesResource, currentProfilesUrl) {
            var dealerId = 123456;
            var dealer = {dealerId: dealerId};
            var expectedRequest = currentProfilesUrl.replace(':dealerId', dealerId);
            expectedRequest += '?ran=' + new Date().getTime();
            var expectedList = [{name: 'U.T. Victory Profile'}];

            httpBackend.when('GET', expectedRequest).respond(expectedList);

            var promise = dealerProfilesResource.queryCurrent(dealer);

            promise.then(function(actualList) {
                expect(actualList.length).toEqual(expectedList.length);
            });

            httpBackend.flush();
        }));
    });

    describe('queryHistory', function() {
        it('returns a promise with a list of profiles of a specified dealer', inject(function(dealerProfilesResource, historyProfilesUrl) {
            var dealerId = 123456;
            var dealer = {dealerId: dealerId};
            var expectedRequest = historyProfilesUrl.replace(':dealerId', dealerId);
            expectedRequest += '?ran=' + new Date().getTime();
            var expectedList = [{name: 'U.T. Victory Profile'}];

            httpBackend.when('GET', expectedRequest).respond(expectedList);

            var promise = dealerProfilesResource.queryHistory(dealer);

            promise.then(function(actualList) {
                expect(actualList.length).toEqual(expectedList.length);
            });

            httpBackend.flush();
        }));
    });
});
