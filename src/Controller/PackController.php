<?php

namespace App\Controller;

use App\Entity\Pack;
use App\Form\PackType;
use App\Repository\PackRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Knp\Component\Pager\PaginatorInterface;

use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Serializer\Encoder\JsonEncoder;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Symfony\Component\Validator\Constraints\Json;


class PackController extends AbstractController
{
    /**
     * @Route("/pack", name="pack_index", methods={"GET"})
     */
    public function index(PackRepository $packRepository, Request $request): Response
    { $pack = $packRepository->findAll();
       /* query NOT result */
      $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($pack);

        return new JsonResponse($formatted);
    }
    
  

    /**
     * @Route("/pack/new", name="pack_new", methods={"GET", "POST"})
     */
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $pack = new Pack();
        $form = $this->createForm(PackType::class, $pack);

        $type = $request->query->get("type");
        $Description= $request->query->get("Description");
        $prix= $request->query->get("prix");
        

        $pack->setType($type);
        $pack->setDescription($Description);
        $pack->setPrix($prix);
   
        $form->handleRequest($request);

        if ($pack) {
            $entityManager->persist($pack);
            $entityManager->flush();
            $serializer = new Serializer([new ObjectNormalizer()]);
            $formatted = $serializer->normalize($pack);
            return new JsonResponse($formatted);
        }else{
            return new JsonResponse("not added");
        }
    
        }

 

    /**
     * @Route("/pack/edit/{id}", name="pack_edit", methods={"GET", "POST"})
     */
    public function edit(Request $request, Pack $pack, EntityManagerInterface $entityManager): Response
    {
        $id = $request->get("id");
        $pack = new Pack();
        $pack = $this->getDoctrine()
            ->getRepository(Pack::class)
            ->find($id);


            $type = $request->query->get("type");
            $Description= $request->query->get("Description");
            $prix= $request->query->get("prix");
            
    
            $pack->setType($type);
            $pack->setDescription($Description);
            $pack->setPrix($prix);

        $form = $this->createForm(PackType::class, $pack);
        $form->handleRequest($request);
        $entityManager = $this->getDoctrine()->getManager();
        $entityManager->flush();
        $form->handleRequest($request);
        if ($pack) {
             
            $serialize = new Serializer([new ObjectNormalizer()]);
                $formatted = $serialize->normalize($pack);
                return new JsonResponse($formatted);
               // return $this->redirectToRoute('admin_list');
            }
            return new JsonResponse("not update");
    }

    /**
     * @Route("/Delete/pack/{id}", name="pack_delete")
     * *Method({"DELETE"})
     */
    public function delete(Request $request, Pack $pack, EntityManagerInterface $entityManager): Response
    {
        $id = $request->get("id");

        $pack = $this->getDoctrine()
            ->getRepository(Pack::class)
            ->find($id);

            $em = $this->getDoctrine()->getManager();
            if($pack ) {
                $em->remove($pack);
                $em->flush();
   
                $serialize = new Serializer([new ObjectNormalizer()]);
                $formatted = $serialize->normalize("pack a ete supprimee avec success.");
                return new JsonResponse($formatted);
   
            }
            return new JsonResponse("not deleted");
            
    }
}