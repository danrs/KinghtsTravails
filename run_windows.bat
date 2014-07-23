@echo off
pushd src
IF "%1"=="" (
	echo running sample input: A8 B7
	java com.company.Main A8 B7
) ELSE (
	rem run user input
	java com.company.Main %1 %2
)

:end
popd
pause