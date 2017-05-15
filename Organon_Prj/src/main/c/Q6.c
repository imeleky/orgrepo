/*
 ============================================================================
 Name        : Q6.c
 Author      : imy
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

#include <jni.h>
#include <stdio.h>
#include "include/Q6.h"
#include<time.h>

/*JNIEXPORT void JNICALL Java_Q6_cfunction
(JNIEnv *env, jobject jobj)
 {
     printf("Hello World from CC!\n");
     return;
 }
*/


JNIEXPORT jlong JNICALL
Java_Q6_cCalculateDotProduct
(JNIEnv *env, jobject jobj, jintArray jArrInt1,jintArray jArrInt2)
{

	struct timespec start;
	struct timespec end;

	// Get length of jArrInt
	size_t lenArrInt1 = env->GetArrayLength(jArrInt1);
	size_t lenArrInt2 = env->GetArrayLength(jArrInt2);

	// Get jintArray element
	jint* arrInt1 = env->GetIntArrayElements(jArrInt1, NULL);
	jint* arrInt2 = env->GetIntArrayElements(jArrInt2, NULL);

	long long sum = 0;

    if((clock_gettime( CLOCK_REALTIME, &start)) == -1 )
      perror("clock gettime\n");

	for (int i = 0; i < lenArrInt1; i++)
	  sum += arrInt1[i]*arrInt2[i];

    if((clock_gettime( CLOCK_REALTIME, &end)) == -1 )
      perror("clock gettime\n");

	unsigned long long time = ((end.tv_nsec-start.tv_nsec));
    printf(" Time Cost   on UnManaged Method   --> %lu",time);

	env->ReleaseIntArrayElements(jArrInt1,arrInt1,NULL);
	env->ReleaseIntArrayElements(jArrInt2,arrInt2,NULL);

	return sum ;
}
