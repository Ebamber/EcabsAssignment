I spent some time playing around with the JSON for both Instant and OffsetDateTime formats and in the end came up with custom 
deserializers and serializers for both (an annotation approach wasn't working, 
Spring kept complaining about it).

Disclaimer:

For all Instant timestamp fields please input time in the format:

    "yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS"
    
For OffsetDateTime formats:

    "yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSSXXXX"
    
otherwise Spring will complain about a misconfigured timestamp.