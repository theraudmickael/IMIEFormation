#include <jni.h>
#include <stdio.h>
#include <string.h>

JNIEXPORT jint JNICALL Java_fr_imie_imiendk_MainActivity_isPrimeC(JNIEnv *env, jobject obj, jint n) {

	int b = 0;
	int c;

	for (c = 2 ; c <= n - 1 ; c++)
	{
		if ( n%c == 0 )
		{
			break;
		}
	}

	if ( c == n )
		b = 1;

	return b;

}

JNIEXPORT jintArray JNICALL Java_fr_imie_imiendk_MainActivity_getPrimesC(JNIEnv *env, jobject obj, jint n) {

	jintArray res;
	jint nbArray[n];
	int i = 3, count, c, cpt = 0;

	res = (*env)->NewIntArray(env,n);

	if ( n >= 1 )
	{
		nbArray[cpt] = 2;
		cpt ++;
	}

	for ( count = 2 ; count <= n ;  count ++)
	{
		for ( c = 2 ; c <= i - 1 ; c++ )
		{
			if ( i%c == 0 )
				break;
		}

		if ( c == i )
		{
			nbArray[cpt] = i;
			cpt++;
			count++;
		}

		i++;
	}

	(*env)->SetIntArrayRegion(env, res, 0, n, nbArray);
	return res;
}
