package com.hisi.arcsoft;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.hisi.arcsoft.utils.ImageLoader;
import com.hisi.arcsoft.utils.ImageLoader.BufferInfo;
import com.sun.jna.Memory;
import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.FloatByReference;
import com.sun.jna.ptr.PointerByReference;


public class AFD_FSDK_USE {
	public static final int FD_WORKBUF_SIZE = 20 * 1024 * 1024;
	public static final int FR_WORKBUF_SIZE = 40 * 1024 * 1024;
	public static final int MAX_FACE_NUM = 50;

	public static final boolean bUseYUVFile = false;
	
	public static float doFD(String filePathA, String filePathB, String appid,
			String fd_sdkkey, String fr_sdkkey) {
		// Init Engine
		Pointer pFDWorkMem = CLibrary.INSTANCE.malloc(FD_WORKBUF_SIZE);
		Pointer pFRWorkMem = CLibrary.INSTANCE.malloc(FR_WORKBUF_SIZE);

		PointerByReference phFDEngine = new PointerByReference();
		NativeLong ret = AFD_FSDKLibrary.INSTANCE.AFD_FSDK_InitialFaceEngine(
				appid, fd_sdkkey, pFDWorkMem, FD_WORKBUF_SIZE, phFDEngine,
				_AFD_FSDK_OrientPriority.AFD_FSDK_OPF_0_HIGHER_EXT, 16,
				MAX_FACE_NUM);
		if (ret.intValue() != 0) {
			CLibrary.INSTANCE.free(pFDWorkMem);
			CLibrary.INSTANCE.free(pFRWorkMem);
			System.out.println("AFD_FSDK_InitialFaceEngine ret == " + ret);
			System.exit(0);
		}

		// print FDEngine version
		Pointer hFDEngine = phFDEngine.getValue();

		PointerByReference phFREngine = new PointerByReference();
		ret = AFR_FSDKLibrary.INSTANCE.AFR_FSDK_InitialEngine(appid, fr_sdkkey,
				pFRWorkMem, FR_WORKBUF_SIZE, phFREngine);
		if (ret.intValue() != 0) {
			AFD_FSDKLibrary.INSTANCE.AFD_FSDK_UninitialFaceEngine(hFDEngine);
			CLibrary.INSTANCE.free(pFDWorkMem);
			CLibrary.INSTANCE.free(pFRWorkMem);
			System.out.println("AFD_FSDK_InitialFaceEngine ret == " + ret);
			System.exit(0);
		}

		// print FREngine version
		Pointer hFREngine = phFREngine.getValue();

		// load Image Data
		ASVLOFFSCREEN inputImgA;
		ASVLOFFSCREEN inputImgB;

		inputImgA = loadImage(filePathA);
		inputImgB = loadImage(filePathB);

		float result = compareFaceSimilarity(hFDEngine, hFREngine, inputImgA,
				inputImgB);
		//System.out.println("similarity between faceA and faceB is " + result);
		// release Engine
		AFD_FSDKLibrary.INSTANCE.AFD_FSDK_UninitialFaceEngine(hFDEngine);
		AFR_FSDKLibrary.INSTANCE.AFR_FSDK_UninitialEngine(hFREngine);

		CLibrary.INSTANCE.free(pFDWorkMem);
		CLibrary.INSTANCE.free(pFRWorkMem);
		return result;
	}
public static byte[] generateByte(String imageUrl, String appid,
		String fd_sdkkey, String fr_sdkkey, ConfigInfo configInfo){
	// Init Engine
			if (AFD_FSDK_COMMON.sdk.isEmpty()) {
				String t1 = configInfo.getFdsdk();
				String t2 = configInfo.getFrsdk();
				AFD_FSDK_COMMON.sdk.put(0, t1);
				AFD_FSDK_COMMON.sdk.put(1, t2);
			}
			Pointer pFDWorkMem = CLibrary.INSTANCE.malloc(FD_WORKBUF_SIZE);
			Pointer pFRWorkMem = CLibrary.INSTANCE.malloc(FR_WORKBUF_SIZE);

			PointerByReference phFDEngine = new PointerByReference();
			NativeLong ret = AFD_FSDKLibrary.INSTANCE.AFD_FSDK_InitialFaceEngine(
					appid, fd_sdkkey, pFDWorkMem, FD_WORKBUF_SIZE, phFDEngine,
					_AFD_FSDK_OrientPriority.AFD_FSDK_OPF_0_HIGHER_EXT, 16,
					MAX_FACE_NUM);
			if (ret.intValue() != 0) {
				CLibrary.INSTANCE.free(pFDWorkMem);
				CLibrary.INSTANCE.free(pFRWorkMem);
				System.out.println("AFD_FSDK_InitialFaceEngine ret == " + ret);
				System.exit(0);
			}

			// print FDEngine version
			Pointer hFDEngine = phFDEngine.getValue();

			PointerByReference phFREngine = new PointerByReference();
			ret = AFR_FSDKLibrary.INSTANCE.AFR_FSDK_InitialEngine(appid, fr_sdkkey,
					pFRWorkMem, FR_WORKBUF_SIZE, phFREngine);
			if (ret.intValue() != 0) {
				AFD_FSDKLibrary.INSTANCE.AFD_FSDK_UninitialFaceEngine(hFDEngine);
				CLibrary.INSTANCE.free(pFDWorkMem);
				CLibrary.INSTANCE.free(pFRWorkMem);
				System.out.println("AFD_FSDK_InitialFaceEngine ret == " + ret);
				System.exit(0);
			}

			// print FREngine version
			Pointer hFREngine = phFREngine.getValue();
			// load Image Data
			ASVLOFFSCREEN inputImgA;
			inputImgA = loadImage(imageUrl);
			FaceInfo[] faceInfosA = doFaceDetection(hFDEngine, inputImgA);
			if (faceInfosA.length < 1) {
				System.out.println("no face in Image ");
				return null;
			}
			AFR_FSDK_FACEMODEL faceFeatureA = extractFRFeature(hFREngine,
					inputImgA, faceInfosA[0]);
			if (faceFeatureA == null) {
				System.out.println("extract face feature in Image A faile ");
				return null;
			}
			try {
				byte[] featureByteArray=faceFeatureA.toByteArray();
				return featureByteArray;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
}
	public static FaceInfo[] doFaceDetection(Pointer hFDEngine,
			ASVLOFFSCREEN inputImg) {
		FaceInfo[] faceInfo = new FaceInfo[0];

		PointerByReference ppFaceRes = new PointerByReference();
		NativeLong ret = AFD_FSDKLibrary.INSTANCE
				.AFD_FSDK_StillImageFaceDetection(hFDEngine, inputImg,
						ppFaceRes);
		if (ret.intValue() != 0) {
			System.out
					.println("AFD_FSDK_StillImageFaceDetection ret == " + ret);
			return faceInfo;
		}

		AFD_FSDK_FACERES faceRes = new AFD_FSDK_FACERES(ppFaceRes.getValue());
		if (faceRes.nFace > 0) {//长度
			faceInfo = new FaceInfo[faceRes.nFace];
			for (int i = 0; i < faceRes.nFace; i++) {
				MRECT rect = new MRECT(new Pointer(
						Pointer.nativeValue(faceRes.rcFace.getPointer())
								+ faceRes.rcFace.size() * i));
				int orient = faceRes.lfaceOrient.getPointer().getInt(i * 4);
				faceInfo[i] = new FaceInfo();

				faceInfo[i].left = rect.left;
				faceInfo[i].top = rect.top;
				faceInfo[i].right = rect.right;
				faceInfo[i].bottom = rect.bottom;
				faceInfo[i].orient = orient;

				System.out.println(String
						.format("%d (%d %d %d %d) orient %d", i, rect.left,
								rect.top, rect.right, rect.bottom, orient));
			}
		}
		return faceInfo;
	}

	public static AFR_FSDK_FACEMODEL extractFRFeature(Pointer hFREngine,
			ASVLOFFSCREEN inputImg, FaceInfo faceInfo) {

		AFR_FSDK_FACEINPUT faceinput = new AFR_FSDK_FACEINPUT();
		faceinput.lOrient = faceInfo.orient;
		faceinput.rcFace.left = faceInfo.left;
		faceinput.rcFace.top = faceInfo.top;
		faceinput.rcFace.right = faceInfo.right;
		faceinput.rcFace.bottom = faceInfo.bottom;

		AFR_FSDK_FACEMODEL faceFeature = new AFR_FSDK_FACEMODEL();
		NativeLong ret = AFR_FSDKLibrary.INSTANCE.AFR_FSDK_ExtractFRFeature(
				hFREngine, inputImg, faceinput, faceFeature);
		if (ret.intValue() != 0) {
			System.out.println("AFR_FSDK_ExtractFRFeature ret == " + ret);
			return null;
		}

		try {
			return faceFeature.deepCopy();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public static float compareFaceSimilarity(Pointer hFDEngine,
			Pointer hFREngine, ASVLOFFSCREEN inputImgA, ASVLOFFSCREEN inputImgB) {
		// Do Face Detect
		
		try {
			FaceInfo[] faceInfosA = doFaceDetection(hFDEngine, inputImgA);
			if (faceInfosA.length < 1) {
				System.out.println("no face in Image A ");
				return 0.0f;
			}

			FaceInfo[] faceInfosB = doFaceDetection(hFDEngine, inputImgB);
			if (faceInfosB.length < 1) {
				System.out.println("no face in Image B ");
				return 0.0f;
			}

			// Extract Face Feature
			AFR_FSDK_FACEMODEL faceFeatureA = extractFRFeature(hFREngine,
					inputImgA, faceInfosA[0]);
			if (faceFeatureA == null) {
				System.out.println("extract face feature in Image A faile ");
				return 0.0f;
			}
			byte[] featureByteArray=faceFeatureA.toByteArray();
			System.out.println(featureByteArray+"123");
			AFR_FSDK_FACEMODEL facefeatureX = AFR_FSDK_FACEMODEL.fromByteArray(featureByteArray);
			AFR_FSDK_FACEMODEL faceFeatureB = extractFRFeature(hFREngine,
					inputImgB, faceInfosB[0]);
			if (faceFeatureB == null) {
				System.out.println("extract face feature in Image B faile ");
				faceFeatureA.freeUnmanaged();
				return 0.0f;
			}

			// calc similarity between faceA and faceB   faceFeatureA
			FloatByReference fSimilScore = new FloatByReference(0.0f);
			NativeLong ret = AFR_FSDKLibrary.INSTANCE.AFR_FSDK_FacePairMatching(
					hFREngine, facefeatureX, faceFeatureB, fSimilScore);
			
			faceFeatureA.freeUnmanaged();
			faceFeatureB.freeUnmanaged();
			if (ret.intValue() != 0) {
				System.out
						.println("AFR_FSDK_FacePairMatching failed:ret == " + ret);
				return 0.0f;
			}
			return fSimilScore.getValue();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}

		
	}

	public static ASVLOFFSCREEN loadYUVImage(String yuv_filePath,
			int yuv_width, int yuv_height, int yuv_format) {
		int yuv_rawdata_size = 0;

		ASVLOFFSCREEN inputImg = new ASVLOFFSCREEN();
		inputImg.u32PixelArrayFormat = yuv_format;
		inputImg.i32Width = yuv_width;
		inputImg.i32Height = yuv_height;
		if (ASVL_COLOR_FORMAT.ASVL_PAF_I420 == inputImg.u32PixelArrayFormat) {
			inputImg.pi32Pitch[0] = inputImg.i32Width;
			inputImg.pi32Pitch[1] = inputImg.i32Width / 2;
			inputImg.pi32Pitch[2] = inputImg.i32Width / 2;
			yuv_rawdata_size = inputImg.i32Width * inputImg.i32Height * 3 / 2;
		} else if (ASVL_COLOR_FORMAT.ASVL_PAF_NV12 == inputImg.u32PixelArrayFormat) {
			inputImg.pi32Pitch[0] = inputImg.i32Width;
			inputImg.pi32Pitch[1] = inputImg.i32Width;
			yuv_rawdata_size = inputImg.i32Width * inputImg.i32Height * 3 / 2;
		} else if (ASVL_COLOR_FORMAT.ASVL_PAF_NV21 == inputImg.u32PixelArrayFormat) {
			inputImg.pi32Pitch[0] = inputImg.i32Width;
			inputImg.pi32Pitch[1] = inputImg.i32Width;
			yuv_rawdata_size = inputImg.i32Width * inputImg.i32Height * 3 / 2;
		} else if (ASVL_COLOR_FORMAT.ASVL_PAF_YUYV == inputImg.u32PixelArrayFormat) {
			inputImg.pi32Pitch[0] = inputImg.i32Width * 2;
			yuv_rawdata_size = inputImg.i32Width * inputImg.i32Height * 2;
		} else {
			System.out.println("unsupported  yuv format");
			System.exit(0);
		}

		// load YUV Image Data from File
		byte[] imagedata = new byte[yuv_rawdata_size];
		File f = new File(yuv_filePath);
		InputStream ios = null;
		try {
			ios = new FileInputStream(f);
			ios.read(imagedata);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error in loading yuv file");
			System.exit(0);
		} finally {
			try {
				if (ios != null) {
					ios.close();
				}
			} catch (IOException e) {
			}
		}

		if (ASVL_COLOR_FORMAT.ASVL_PAF_I420 == inputImg.u32PixelArrayFormat) {
			inputImg.ppu8Plane[0] = new Memory(inputImg.pi32Pitch[0]
					* inputImg.i32Height);
			inputImg.ppu8Plane[0].write(0, imagedata, 0, inputImg.pi32Pitch[0]
					* inputImg.i32Height);
			inputImg.ppu8Plane[1] = new Memory(inputImg.pi32Pitch[1]
					* inputImg.i32Height / 2);
			inputImg.ppu8Plane[1].write(0, imagedata, inputImg.pi32Pitch[0]
					* inputImg.i32Height, inputImg.pi32Pitch[1]
					* inputImg.i32Height / 2);
			inputImg.ppu8Plane[2] = new Memory(inputImg.pi32Pitch[2]
					* inputImg.i32Height / 2);
			inputImg.ppu8Plane[2].write(0, imagedata, inputImg.pi32Pitch[0]
					* inputImg.i32Height + inputImg.pi32Pitch[1]
					* inputImg.i32Height / 2, inputImg.pi32Pitch[2]
					* inputImg.i32Height / 2);
			inputImg.ppu8Plane[3] = Pointer.NULL;
		} else if (ASVL_COLOR_FORMAT.ASVL_PAF_NV12 == inputImg.u32PixelArrayFormat) {
			inputImg.ppu8Plane[0] = new Memory(inputImg.pi32Pitch[0]
					* inputImg.i32Height);
			inputImg.ppu8Plane[0].write(0, imagedata, 0, inputImg.pi32Pitch[0]
					* inputImg.i32Height);
			inputImg.ppu8Plane[1] = new Memory(inputImg.pi32Pitch[1]
					* inputImg.i32Height / 2);
			inputImg.ppu8Plane[1].write(0, imagedata, inputImg.pi32Pitch[0]
					* inputImg.i32Height, inputImg.pi32Pitch[1]
					* inputImg.i32Height / 2);
			inputImg.ppu8Plane[2] = Pointer.NULL;
			inputImg.ppu8Plane[3] = Pointer.NULL;
		} else if (ASVL_COLOR_FORMAT.ASVL_PAF_NV21 == inputImg.u32PixelArrayFormat) {
			inputImg.ppu8Plane[0] = new Memory(inputImg.pi32Pitch[0]
					* inputImg.i32Height);
			inputImg.ppu8Plane[0].write(0, imagedata, 0, inputImg.pi32Pitch[0]
					* inputImg.i32Height);
			inputImg.ppu8Plane[1] = new Memory(inputImg.pi32Pitch[1]
					* inputImg.i32Height / 2);
			inputImg.ppu8Plane[1].write(0, imagedata, inputImg.pi32Pitch[0]
					* inputImg.i32Height, inputImg.pi32Pitch[1]
					* inputImg.i32Height / 2);
			inputImg.ppu8Plane[2] = Pointer.NULL;
			inputImg.ppu8Plane[3] = Pointer.NULL;
		} else if (ASVL_COLOR_FORMAT.ASVL_PAF_YUYV == inputImg.u32PixelArrayFormat) {
			inputImg.ppu8Plane[0] = new Memory(inputImg.pi32Pitch[0]
					* inputImg.i32Height);
			inputImg.ppu8Plane[0].write(0, imagedata, 0, inputImg.pi32Pitch[0]
					* inputImg.i32Height);
			inputImg.ppu8Plane[1] = Pointer.NULL;
			inputImg.ppu8Plane[2] = Pointer.NULL;
			inputImg.ppu8Plane[3] = Pointer.NULL;
		} else {
			System.out.println("unsupported yuv format");
			System.exit(0);
		}

		inputImg.setAutoRead(false);
		return inputImg;
	}

	public static ASVLOFFSCREEN loadImage(String filePath) {
		BufferInfo bufferInfo = ImageLoader.getI420FromFile(filePath);

		ASVLOFFSCREEN inputImg = new ASVLOFFSCREEN();
		inputImg.u32PixelArrayFormat = ASVL_COLOR_FORMAT.ASVL_PAF_I420;
		inputImg.i32Width = bufferInfo.width;
		inputImg.i32Height = bufferInfo.height;
		inputImg.pi32Pitch[0] = inputImg.i32Width;
		inputImg.pi32Pitch[1] = inputImg.i32Width / 2;
		inputImg.pi32Pitch[2] = inputImg.i32Width / 2;
		inputImg.ppu8Plane[0] = new Memory(inputImg.pi32Pitch[0]
				* inputImg.i32Height);
		inputImg.ppu8Plane[0].write(0, bufferInfo.base, 0,
				inputImg.pi32Pitch[0] * inputImg.i32Height);
		inputImg.ppu8Plane[1] = new Memory(inputImg.pi32Pitch[1]
				* inputImg.i32Height / 2);
		inputImg.ppu8Plane[1].write(0, bufferInfo.base, inputImg.pi32Pitch[0]
				* inputImg.i32Height, inputImg.pi32Pitch[1]
				* inputImg.i32Height / 2);
		inputImg.ppu8Plane[2] = new Memory(inputImg.pi32Pitch[2]
				* inputImg.i32Height / 2);
		inputImg.ppu8Plane[2].write(0, bufferInfo.base, inputImg.pi32Pitch[0]
				* inputImg.i32Height + inputImg.pi32Pitch[1]
				* inputImg.i32Height / 2, inputImg.pi32Pitch[2]
				* inputImg.i32Height / 2);
		inputImg.ppu8Plane[3] = Pointer.NULL;

		inputImg.setAutoRead(false);
		return inputImg;
	}

	public static class FaceInfo {
		public int left;
		public int top;
		public int right;
		public int bottom;
		public int orient;
	}
}
