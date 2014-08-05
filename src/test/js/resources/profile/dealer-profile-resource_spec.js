describe('DealerProfileResource', function() {
    var httpBackend, dealerId, profileId;

    beforeEach(function() {
        angular.mock.module('sellIn.resources.dealerProfile');

        angular.mock.inject(function($injector) {
            httpBackend = $injector.get('$httpBackend');
        });
    });

    afterEach(function() {
        httpBackend.verifyNoOutstandingExpectation();
        httpBackend.verifyNoOutstandingRequest();
    });

    describe('get', function() {
        it('returns a promise with a single profile of a specified dealer', inject(function(dealerProfileResource, profileUrl) {
            var profileId = 999;
            var dealerId = 888;
            var profile = {profileId: profileId, dealerId: dealerId};
            var expectedRequest = profileUrl
                .replace(':profileId', profileId)
                .replace(':dealerId', dealerId);
            var expectedProfile = { name: 'U.T. Victory Profile'};

            httpBackend.when('GET', expectedRequest).respond(expectedProfile);
            httpBackend.expectGET(expectedRequest);

            var promise = dealerProfileResource.get(profile);

            promise.then(function(actualProfile) {
                expect(actualProfile.name).toEqual(expectedProfile.name);
            });

            httpBackend.flush();
        }));
    });

    describe('save', function() {
        it('returns a promise with a single object containing a message and array of details', inject(function(dealerProfileResource, profileSaveUrl) {
            var details = [{name: 'UT Detail'}, {name: 'UT Detail 2'}];
            var expectedRequest = profileSaveUrl;
            var expectedMessage = 'UT response message';
            var expectedDetails = [{id: 'UT1', name: 'UT Detail'}, {id: 'UT2', name: 'UT Detail 2'}];
            var expectedResponse = {message: expectedMessage, objects: expectedDetails};

            httpBackend.when('POST', expectedRequest).respond(expectedResponse);
            httpBackend.expectPOST(expectedRequest);

            var promise = dealerProfileResource.save(details);

            promise.then(function(actualResponse) {
                expect(actualResponse.message).toEqual(expectedMessage);
                for(var i = 0; i < actualResponse.objects.length; i++) {
                    var returnedDetail = actualResponse.objects[i];
                    var expectedDetail = expectedDetails[i];
                    expect(returnedDetail.id).toEqual(expectedDetail.id);
                    expect(returnedDetail.name).toEqual(expectedDetail.name);
                }
            });

            httpBackend.flush();
        }));
    });
    
    describe('submit', function() {
        it('returns a promise with a single object containing a message and array of details', inject(function(dealerProfileResource, profileSubmitUrl) {
            var details = [{name: 'UT Detail'}, {name: 'UT Detail 2'}];
            var expectedRequest = profileSubmitUrl;
            var expectedMessage = 'UT response message';
            var expectedDetails = [{id: 'UT1', name: 'UT Detail'}, {id: 'UT2', name: 'UT Detail 2'}];
            var expectedResponse = {message: expectedMessage, objects: expectedDetails};

            httpBackend.when('POST', expectedRequest).respond(expectedResponse);
            httpBackend.expectPOST(expectedRequest);

            var promise = dealerProfileResource.submit(details);

            promise.then(function(actualResponse) {
                expect(actualResponse.message).toEqual(expectedMessage);
                for(var i = 0; i < actualResponse.objects.length; i++) {
                    var returnedDetail = actualResponse.objects[i];
                    var expectedDetail = expectedDetails[i];
                    expect(returnedDetail.id).toEqual(expectedDetail.id);
                    expect(returnedDetail.name).toEqual(expectedDetail.name);
                }
            });

            httpBackend.flush();
        }));
    });
    
    describe('dsmSave', function() {
        it('returns a promise with a single object containing a message and array of details', inject(function(dealerProfileResource, profileDsmSaveUrl) {
            var details = [{name: 'UT Detail'}, {name: 'UT Detail 2'}];
            var expectedRequest = profileDsmSaveUrl;
            var expectedMessage = 'UT response message';
            var expectedDetails = [{id: 'UT1', name: 'UT Detail'}, {id: 'UT2', name: 'UT Detail 2'}];
            var expectedResponse = {message: expectedMessage, objects: expectedDetails};

            httpBackend.when('POST', expectedRequest).respond(expectedResponse);
            httpBackend.expectPOST(expectedRequest);

            var promise = dealerProfileResource.dsmSave(details);

            promise.then(function(actualResponse) {
                expect(actualResponse.message).toEqual(expectedMessage);
                for(var i = 0; i < actualResponse.objects.length; i++) {
                    var returnedDetail = actualResponse.objects[i];
                    var expectedDetail = expectedDetails[i];
                    expect(returnedDetail.id).toEqual(expectedDetail.id);
                    expect(returnedDetail.name).toEqual(expectedDetail.name);
                }
            });

            httpBackend.flush();
        }));
    });
    
    describe('sendBack',function() {
    	it('returns a promise with a single object containing a message and array of details', inject(function(dealerProfileResource, profileSendBackUrl) {
    		var details = [{name: 'UT Detail'}, {name: 'UT Detail 2'}];
    		var expectedRequest = profileSendBackUrl;
            var expectedMessage = 'UT response message';
            var expectedDetails = [{id: 'UT1', name: 'UT Detail'}, {id: 'UT2', name: 'UT Detail 2'}];
            var expectedResponse = {message: expectedMessage, objects: expectedDetails};
            
            httpBackend.when('POST', expectedRequest).respond(expectedResponse);
            httpBackend.expectPOST(expectedRequest);
            
            var promise = dealerProfileResource.sendBack(details);
            
            promise.then(function(actualResponse) {
                expect(actualResponse.message).toEqual(expectedMessage);
                for(var i = 0; i < actualResponse.objects.length; i++) {
                    var returnedDetail = actualResponse.objects[i];
                    var expectedDetail = expectedDetails[i];
                    expect(returnedDetail.id).toEqual(expectedDetail.id);
                    expect(returnedDetail.name).toEqual(expectedDetail.name);
                }
            });
            httpBackend.flush();
    	}));
    });

    describe('approveWChanges', function() {
        it('returns a promise with a single object containing a message and array of details', inject(function(dealerProfileResource, profileApproveWChangesUrl) {
            var details = [{name: 'UT Detail'}, {name: 'UT Detail 2'}];
            var expectedRequest = profileApproveWChangesUrl;
            var expectedSuccess = true;
            var expectedMessage = 'UT response message';
            var expectedDetails = [{id: 'UT1', name: 'UT Detail'}, {id: 'UT2', name: 'UT Detail 2'}];
            var expectedResponse = {isSuccessful: expectedSuccess, message: expectedMessage, objects: expectedDetails};

            httpBackend.when('POST', expectedRequest).respond(expectedResponse);
            httpBackend.expectPOST(expectedRequest);

            var promise = dealerProfileResource.approveWChanges(details);

            promise.then(function(actualResponse) {
                expect(actualResponse.message).toEqual(expectedMessage);
                for(var i = 0; i < actualResponse.objects.length; i++) {
                    var returnedDetail = actualResponse.objects[i];
                    var expectedDetail = expectedDetails[i];
                    expect(returnedDetail.id).toEqual(expectedDetail.id);
                    expect(returnedDetail.name).toEqual(expectedDetail.name);
                }
            });
            httpBackend.flush();
        }));
    });

    describe('approveRequested', function() {
        it('returns a promise with a single object containing a message and array of details', inject(function(dealerProfileResource, profileApproveRequestedUrl) {
            var details = [{name: 'UT Detail'}, {name: 'UT Detail 2'}];
            var expectedRequest = profileApproveRequestedUrl;
            var expectedSuccess = true;
            var expectedMessage = 'UT response message';
            var expectedDetails = [{id: 'UT1', name: 'UT Detail'}, {id: 'UT2', name: 'UT Detail 2'}];
            var expectedResponse = {isSuccessful: expectedSuccess, message: expectedMessage, objects: expectedDetails};

            httpBackend.when('POST', expectedRequest).respond(expectedResponse);
            httpBackend.expectPOST(expectedRequest);

            var promise = dealerProfileResource.approveRequested(details);

            promise.then(function(actualResponse) {
                expect(actualResponse.message).toEqual(expectedMessage);
                for(var i = 0; i < actualResponse.objects.length; i++) {
                    var returnedDetail = actualResponse.objects[i];
                    var expectedDetail = expectedDetails[i];
                    expect(returnedDetail.id).toEqual(expectedDetail.id);
                    expect(returnedDetail.name).toEqual(expectedDetail.name);
                }
            });
            httpBackend.flush();
        }));
    });

    describe('submitException', function() {
        it('returns a promise with a single object containing a message and array of details', inject(function(dealerProfileResource, profileSubmitExceptionUrl) {
            var details = [{name: 'UT Detail'}, {name: 'UT Detail 2'}];
            var expectedRequest = profileSubmitExceptionUrl;
            var expectedSuccess = true;
            var expectedMessage = 'UT response message';
            var expectedDetails = [{id: 'UT1', name: 'UT Detail'}, {id: 'UT2', name: 'UT Detail 2'}];
            var expectedResponse = {isSuccessful: expectedSuccess, message: expectedMessage, objects: expectedDetails};

            httpBackend.when('POST', expectedRequest).respond(expectedResponse);
            httpBackend.expectPOST(expectedRequest);

            var promise = dealerProfileResource.submitException(details);

            promise.then(function(actualResponse) {
                expect(actualResponse.message).toEqual(expectedMessage);
                for(var i = 0; i < actualResponse.objects.length; i++) {
                    var returnedDetail = actualResponse.objects[i];
                    var expectedDetail = expectedDetails[i];
                    expect(returnedDetail.id).toEqual(expectedDetail.id);
                    expect(returnedDetail.name).toEqual(expectedDetail.name);
                }
            });
            httpBackend.flush();
        }));
    });
    
    describe('rsmSave', function() {
        it('returns a promise with a single object containing a message and array of details', inject(function(dealerProfileResource, profileRsmSaveUrl) {
            var details = [{name: 'UT Detail'}, {name: 'UT Detail 2'}];
            var expectedRequest = profileRsmSaveUrl;
            var expectedMessage = 'UT response message';
            var expectedDetails = [{id: 'UT1', name: 'UT Detail'}, {id: 'UT2', name: 'UT Detail 2'}];
            var expectedResponse = {message: expectedMessage, objects: expectedDetails};

            httpBackend.when('POST', expectedRequest).respond(expectedResponse);
            httpBackend.expectPOST(expectedRequest);

            var promise = dealerProfileResource.rsmSave(details);

            promise.then(function(actualResponse) {
                expect(actualResponse.message).toEqual(expectedMessage);
                for(var i = 0; i < actualResponse.objects.length; i++) {
                    var returnedDetail = actualResponse.objects[i];
                    var expectedDetail = expectedDetails[i];
                    expect(returnedDetail.id).toEqual(expectedDetail.id);
                    expect(returnedDetail.name).toEqual(expectedDetail.name);
                }
            });
            httpBackend.flush();
            
        }));
    });
    
    describe('returnDsm',function() {
    	it('returns a promise with a single object containing a message and array of details', inject(function(dealerProfileResource, profileReturnToDsmUrl) {
    		var details = [{name: 'UT Detail'}, {name: 'UT Detail 2'}];
    		var expectedRequest = profileReturnToDsmUrl;
            var expectedMessage = 'UT response message';
            var expectedDetails = [{id: 'UT1', name: 'UT Detail'}, {id: 'UT2', name: 'UT Detail 2'}];
            var expectedResponse = {message: expectedMessage, objects: expectedDetails};
            
            httpBackend.when('POST', expectedRequest).respond(expectedResponse);
            httpBackend.expectPOST(expectedRequest);
            
            var promise = dealerProfileResource.returnDsm(details);
            
            promise.then(function(actualResponse) {
                expect(actualResponse.message).toEqual(expectedMessage);
                for(var i = 0; i < actualResponse.objects.length; i++) {
                    var returnedDetail = actualResponse.objects[i];
                    var expectedDetail = expectedDetails[i];
                    expect(returnedDetail.id).toEqual(expectedDetail.id);
                    expect(returnedDetail.name).toEqual(expectedDetail.name);
                }
            });
            httpBackend.flush();
    	}));
    });
    
    describe('compliant', function() {
        it('returns a promise with a single object containing a message and array of details', inject(function(dealerProfileResource, profileApproveCompliantUrl) {
            var details = [{name: 'UT Detail'}, {name: 'UT Detail 2'}];
            var expectedRequest = profileApproveCompliantUrl;
            var expectedSuccess = true;
            var expectedMessage = 'UT response message';
            var expectedDetails = [{id: 'UT1', name: 'UT Detail'}, {id: 'UT2', name: 'UT Detail 2'}];
            var expectedResponse = {isSuccessful: expectedSuccess, message: expectedMessage, objects: expectedDetails};

            httpBackend.when('POST', expectedRequest).respond(expectedResponse);
            httpBackend.expectPOST(expectedRequest);

            var promise = dealerProfileResource.compliant(details);

            promise.then(function(actualResponse) {
                expect(actualResponse.message).toEqual(expectedMessage);
                for(var i = 0; i < actualResponse.objects.length; i++) {
                    var returnedDetail = actualResponse.objects[i];
                    var expectedDetail = expectedDetails[i];
                    expect(returnedDetail.id).toEqual(expectedDetail.id);
                    expect(returnedDetail.name).toEqual(expectedDetail.name);
                }
            });
            
            httpBackend.flush();
        }));
    });
    
    describe('nonCompliant', function() {
        it('returns a promise with a single object containing a message and array of details', inject(function(dealerProfileResource, profileApproveNonCompliantUrl) {
            var details = [{name: 'UT Detail'}, {name: 'UT Detail 2'}];
            var expectedRequest = profileApproveNonCompliantUrl;
            var expectedSuccess = true;
            var expectedMessage = 'UT response message';
            var expectedDetails = [{id: 'UT1', name: 'UT Detail'}, {id: 'UT2', name: 'UT Detail 2'}];
            var expectedResponse = {isSuccessful: expectedSuccess, message: expectedMessage, objects: expectedDetails};

            httpBackend.when('POST', expectedRequest).respond(expectedResponse);
            httpBackend.expectPOST(expectedRequest);

            var promise = dealerProfileResource.nonCompliant(details);

            promise.then(function(actualResponse) {
                expect(actualResponse.message).toEqual(expectedMessage);
                for(var i = 0; i < actualResponse.objects.length; i++) {
                    var returnedDetail = actualResponse.objects[i];
                    var expectedDetail = expectedDetails[i];
                    expect(returnedDetail.id).toEqual(expectedDetail.id);
                    expect(returnedDetail.name).toEqual(expectedDetail.name);
                }
            });
            
            httpBackend.flush();
        }));
    });
});
