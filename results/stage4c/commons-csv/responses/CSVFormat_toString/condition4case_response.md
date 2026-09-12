{
    "group": "toString Method Test Cases",
    "cases": [
        {
            "input": [],
            "expected": "Delimiter=<,> Escape=<\\> QuoteChar=<\"> QuoteMode=<MINIMAL> RecordSeparator=<\\n> SkipHeaderRecord:false",
            "description": "Test the toString method for the INFORMIX_UNLOAD_CSV format with all components set (delimiter, escape, quote, quote mode, record separator)."
        },
        {
            "input": [],
            "expected": "Delimiter=<,> QuoteChar=<\"> QuoteMode=<MINIMAL> RecordSeparator=<\\n> SkipHeaderRecord:false",
            "description": "Test the toString method for the POSTGRESQL_CSV format where escape character is not set."
        },
        {
            "input": [],
            "expected": "Delimiter=<,> QuoteChar=<\"> QuoteMode=<MINIMAL> RecordSeparator=<\\n> SkipHeaderRecord:false HeaderComments:null Header:null",
            "description": "Test the toString method for the EXCEL format where headerComments and headers are null."
        },
        {
            "input": [],
            "expected": "Delimiter=<|> Escape=<\\> QuoteChar=<\"> RecordSeparator=<\\n> SkipHeaderRecord:false",
            "description": "Test the toString method for the INFORMIX_UNLOAD format where quote mode is not set."
        },
        {
            "input": [],
            "expected": "Delimiter=<\\t> Escape=<\\> RecordSeparator=<\\n> SkipHeaderRecord:false NullString=<NULL>",
            "description": "Test the toString method for the POSTGRESQL_TEXT format where quote character is not set."
        },
        {
            "input": [],
            "expected": "Delimiter=<,> Escape=<\\> QuoteChar=<\"> NullString=<NULL> RecordSeparator=<system.lineSeparator()> SkipHeaderRecord:false",
            "description": "Test the toString method for the ORACLE format with system-dependent line separator."
        },
        {
            "input": [],
            "expected": "Delimiter=<\\t> Escape=<\\> QuoteChar=<\"> QuoteMode=<MINIMAL> RecordSeparator=<\\n> SkipHeaderRecord:false",
            "description": "Test the toString method for the MONGODB_TSV format with tab delimiter."
        },
        {
            "input": [],
            "expected": "Delimiter=<\\t> Escape=<\\> QuoteMode=<ALL_NON_NULL> RecordSeparator=<\\n> SkipHeaderRecord:false NullString=<NULL>",
            "description": "Test the toString method for the MYSQL format where quote character is not set."
        },
        {
            "input": [],
            "expected": "Delimiter=<,> QuoteChar=<\"> RecordSeparator=<\\r\\n> SkipHeaderRecord:false",
            "description": "Test the toString method for the RFC4180 format with CRLF record separator."
        },
        {
            "input": [],
            "expected": "Delimiter=<\\t> RecordSeparator=<\\r\\n> SkipHeaderRecord:false SurroundingSpaces:ignored",
            "description": "Test the toString method for the TDF format where surrounding spaces are ignored."
        }
    ]
}